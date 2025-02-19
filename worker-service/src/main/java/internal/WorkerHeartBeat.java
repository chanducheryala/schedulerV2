package internal;

import grpc.HeartBeatRequest;
import grpc.HeartBeatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WorkerHeartBeat {
    private static final Logger logger = LoggerFactory.getLogger(WorkerHeartBeat.class);
    private static final int WORKER_ID = 1;
    private static final int INITIAL_HEART_BEAT_DELAY = 5;
    private static final int HEART_BEAT_INTERVAL = 10;

    private final TaskManagerStub stub;
    private final ScheduledExecutorService scheduler;

    public WorkerHeartBeat(TaskManagerStub stub) {
        this.stub = stub;
        this.scheduler = Executors.newSingleThreadScheduledExecutor();
    }

    public void periodicHeartBeat() {
        try {
            logger.info("Starting periodic heartbeat...");
            scheduler.scheduleAtFixedRate(this::sendHeartBeat, INITIAL_HEART_BEAT_DELAY, HEART_BEAT_INTERVAL, TimeUnit.SECONDS);
        } catch (Exception ex) {
            logger.error("Error while scheduling heartbeat", ex);
        }
    }

    private void sendHeartBeat() {
        try {
            if (stub.getStub() == null) {
                logger.error("TaskManagerStub is not connected. Skipping heartbeat.");
                return;
            }

            logger.info("Sending heartbeat...");
            HeartBeatRequest request = HeartBeatRequest.newBuilder()
                    .setWorkerId(WORKER_ID)
                    .build();

            HeartBeatResponse response = stub.getStub().sendHeartBeat(request);

            if (response.getAcknowledge()) {
                logger.info("Coordinator acknowledged heartbeat, workerId: {}", WORKER_ID);
            } else {
                logger.warn("Coordinator did not acknowledge heartbeat, workerId: {}", WORKER_ID);
            }
        } catch (Exception ex) {
            logger.error("Error while sending heartbeat for workerId {}", WORKER_ID, ex);
        }
    }

    public void stopHeartBeat() {
        scheduler.shutdown();
        logger.info("Heartbeat scheduler stopped.");
    }
}
