package core;


import grpc.*;
import io.grpc.stub.StreamObserver;

public class CoordinatorService extends CoordinatorServiceGrpc.CoordinatorServiceImplBase {
    @Override
    public void updateTask(UpdateTaskRequest request, StreamObserver<UpdateTaskResponse> responseObserver) {

    }

    @Override
    public void sendHeartBeat(HeartBeatRequest request, StreamObserver<HeartBeatResponse> responseObserver) {

    }
}
