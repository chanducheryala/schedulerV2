package internal;


import grpc.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class CoordinatorService extends CoordinatorServiceGrpc.CoordinatorServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(CoordinatorService.class);
    private static final HashMap<Long, WorkerServiceGrpc.WorkerServiceBlockingStub> workers = new HashMap<>();

    @Override
    public void updateTask(UpdateTaskRequest request, StreamObserver<UpdateTaskResponse> responseObserver) {

    }

    @Override
    public void sendHeartBeat(HeartBeatRequest request, StreamObserver<HeartBeatResponse> responseObserver) {
        long workerId = request.getWorkerId();
        String hostname = request.getHostname();
        String port = request.getPort();
        logger.info("workerId : {} hotsname : {} and port is {}", workerId, hostname, port);
        if(!workers.containsKey(workerId)) {
            ManagedChannel workerChannel = ManagedChannelBuilder.forAddress(hostname, Integer.parseInt(port)).usePlaintext().build();
            WorkerServiceGrpc.WorkerServiceBlockingStub stub = WorkerServiceGrpc.newBlockingStub(workerChannel);
            workers.put(workerId, stub);
        } else {
            // TODO

            HeartBeatResponse response = HeartBeatResponse.newBuilder()
                    .setAcknowledge(true).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
