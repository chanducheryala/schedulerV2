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
    private static final int WORKERS = 10;
    private static final int WORKER_ID = 1;
    private static final int INITIAL_HEART_BEAT_DELAY = 5;
    private static final int HEART_BEAT_INTERVAL = 10;

    private TaskManagerStub stub;


    public Runnable periodicHeartBeat = () -> {
       try {
           ScheduledExecutorService ticker = Executors.newScheduledThreadPool(1);
           ticker.scheduleAtFixedRate(this::sendHeartBeat, 5, 10, TimeUnit.SECONDS);
       } catch (Exception ex) {

       }
    };


    private void sendHeartBeat() {
        try {
            HeartBeatRequest.Builder heartBeatRequest = HeartBeatRequest.newBuilder()
                    .setWorkerId(WORKER_ID);
            HeartBeatResponse response = stub.getStub().sendHeartBeat(heartBeatRequest.build());
            if(response.getAcknowledge()) {
                logger.info("Coordinator server acknowledged Worker server heartBeat, workerId : {}", WORKER_ID);
            }
        } catch (Exception ex) {
            logger.error("error while sending heartbeat of workerId {}", WORKER_ID);
        }
    }

}
