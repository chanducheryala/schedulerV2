package coordinatorServer.coorinator;


import grpcserver.CoordinatorServiceGrpc;
import grpcserver.HeartBeatRequest;
import grpcserver.UpdateTaskRequest;
import grpcserver.UpdateTaskResponse;
import io.grpc.stub.StreamObserver;

public class CoordinatorService extends CoordinatorServiceGrpc.CoordinatorServiceImplBase {
    @Override
    public void updateTask(UpdateTaskRequest request, StreamObserver<UpdateTaskResponse> responseObserver) {
    }

    @Override
    public void sendHeartBeat(HeartBeatRequest request, StreamObserver<grpcserver.HeartBeatResponse> responseObserver) {
    }
}
