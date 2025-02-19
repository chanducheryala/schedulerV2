package internal;

import grpc.HeartBeatRequest;
import grpc.HeartBeatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WorkerPool {
    private static final Logger logger = LoggerFactory.getLogger(WorkerPool.class);
    private static final int WORKER_ID = 1;
    private static final int WORKERS = 10;
    private static final int INITIAL_HEART_BEAT_DELAY = 5;
    private static final int HEART_BEAT_INTERVAL = 10;

    private  TaskManagerStub taskManagerStub;

    public WorkerPool() {
        taskManagerStub = new TaskManagerStub();
    }

    public void startWorkerPool() {
        ExecutorService workerThreads = Executors.newFixedThreadPool(WORKERS);
        for(int worker = 0; worker < WORKERS; worker++) {
            workerThreads.submit(this::startWorker);
        }
        this.handleHeartBeat();
    }


    private void handleHeartBeat() {
        ScheduledExecutorService ticker = Executors.newScheduledThreadPool(1);
        try {
            ticker.scheduleAtFixedRate(this::sendHeartBeat, INITIAL_HEART_BEAT_DELAY, HEART_BEAT_INTERVAL, TimeUnit.SECONDS);
        } catch (Exception ex) {
            logger.error("error : {}", ex.getStackTrace());
        }
    }

    private void sendHeartBeat() {
        try {
            HeartBeatRequest.Builder heartBeatRequest = HeartBeatRequest.newBuilder()
                    .setWorkerId(WORKER_ID);
            HeartBeatResponse response = taskManagerStub.getStub().sendHeartBeat(heartBeatRequest.build());
            if(response.getAcknowledge()) {
                logger.info("Coordinator server acknowledged Worker server heartBeat, workerId : {}", WORKER_ID);
            }
        } catch (Exception ex) {
            logger.error("error while sending heartbeat of workerId {}", WORKER_ID);
        }
    }

    private void startWorker() {
        try {
            logger.info("worker Thread id : {} and Thread Name : {}", Thread.currentThread().getId(), Thread.currentThread().getName());
        } catch (Exception ex) {
            logger.error("error while starting worker {}", ex.getStackTrace());
        }
    }
}
