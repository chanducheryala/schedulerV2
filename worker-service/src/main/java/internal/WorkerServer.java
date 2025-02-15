package internal;

import grpc.CoordinatorServiceGrpc;
import grpc.HeartBeatRequest;
import grpc.HeartBeatResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.*;

/*  every HEART_BEAT_INTERVAL seconds worker service heart beat coordinator server
*   stub => the grpc client connection to talk with grpc server / coordinator
*   handleWorkerPool -> It create WORKERS threads to waits for tasks and process
*/


public class WorkerServer {
    private static final Logger logger = LoggerFactory.getLogger(WorkerServer.class);
    private static final int WORKERS = 10;
    private static final int WORKER_ID = 1;
    private static final int INITIAL_HEART_BEAT_DELAY = 5;
    private static final int HEART_BEAT_INTERVAL = 10;
    private static ArrayList<String> workers = new ArrayList<>();

    private static WorkerSocketServer workerSocketServer;

    private CoordinatorServiceGrpc.CoordinatorServiceBlockingStub stub;

    public WorkerServer(CoordinatorServiceGrpc.CoordinatorServiceBlockingStub stub) {
        this.stub = stub;
        workerSocketServer = new WorkerSocketServer();
    }

    public void start() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        try {
            logger.info("started the worker server");
            workerSocketServer.start();
            executor.submit(this::handleWoorkPool);
        } catch (Exception ex) {

        }
    }

    private void handleWoorkPool() {
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
                   .setWorkerId(WORKER_ID)
                   .setHostname(workerSocketServer.getHostname())
                   .setPort(workerSocketServer.getServerPort());
           HeartBeatResponse response = stub.sendHeartBeat(heartBeatRequest.build());
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
