package internal;

import grpc.TaskRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.*;

/* checks periodically heartbeats and remove inactive worker services */
public class Workerpool {
    private static final Logger logger = LoggerFactory.getLogger(Workerpool.class);
    private static final int HEARTBEAT_CHECK_INTERVAL = 15;
    private static final int WORKER_TIMEOUT = 30;
    private final ConcurrentHashMap<Long, Long> workersLastHeartBeat = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Long, WorkerServerStub> workerConnections = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final CopyOnWriteArrayList<Long> workerIds = new CopyOnWriteArrayList<>();
    private static int roundRobinIndex = 0;

    public Workerpool() {
       scheduler.scheduleAtFixedRate(this::manageWorkerpool, HEARTBEAT_CHECK_INTERVAL, HEARTBEAT_CHECK_INTERVAL, TimeUnit.SECONDS);
    }

    public void updateWorkerHeartBeat(long workerId) {
        if(!workersLastHeartBeat.containsKey(workerId)) {
            workerIds.add(workerId);
        }
        workersLastHeartBeat.put(workerId, System.currentTimeMillis());
        logger.info("Updated heartbeat of workerId : {}", workerId);
    }


    public void manageWorkerpool() {
        long currentTime = System.currentTimeMillis();
        workersLastHeartBeat.entrySet().removeIf((worker) -> {
            boolean isActiveWorker = (currentTime - worker.getValue()) > (WORKER_TIMEOUT * 1000);
            if(isActiveWorker) {
                workerIds.remove(worker.getKey());
                logger.info("removing worker server {}", worker.getKey());
            }
            return isActiveWorker;
        });
    }

    public void sendTaskToWorker(String taskId, String task) {
        try {
            long workerId = this.getWorkerId();
            TaskRequest taskRequest = TaskRequest.newBuilder()
                    .setTaskId(taskId)
                    .setData(task)
                    .build();
            workerConnections
                    .get(workerId)
                    .getStub()
                    .sendTask(taskRequest);

        } catch (Exception ex) {
            logger.error("error while sending task to worker", ex);
        }
    }

    private long getWorkerId() {
        int totalWorkers = workerIds.size();
        int workerId = roundRobinIndex;
        roundRobinIndex = ((roundRobinIndex + 1) % totalWorkers);
        return workerId;
    }

    public boolean getWorkerConnection(long workerId) {
        return workerConnections.containsKey(workerId);
    }

    public void setWorkerConnection(long workerId, WorkerServerStub workerServerStub) {
        workerConnections.put(workerId, workerServerStub);
    }

    private void shutdown() {
        logger.info("Shutting down Worker pool.");
        scheduler.shutdown();
    }

}
