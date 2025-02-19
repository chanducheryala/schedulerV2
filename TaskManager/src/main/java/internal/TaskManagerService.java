package internal;

import grpc.*;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TaskManagerService extends TaskManagerServiceGrpc.TaskManagerServiceImplBase {
    private static final Logger logger = LoggerFactory.getLogger(TaskManagerService.class);
    private final Workerpool workerpool = new Workerpool();

    @Override
    public void updateTask(UpdateTaskRequest request, StreamObserver<UpdateTaskResponse> responseObserver) {

    }

    @Override
    public void sendHeartBeat(HeartBeatRequest request, StreamObserver<HeartBeatResponse> responseObserver) {
        long workerId = request.getWorkerId();
        String hostname = request.getHostname();
        String port = request.getPort();
        logger.info("Heartbeat sent by workerId : {} hostname : {} and port is {}", workerId, hostname, port);
        workerpool.updateWorkerHeartBeat(workerId);
        if(!workerpool.getWorkerConnection(workerId)) {
            WorkerServerStub workerServerStub = new WorkerServerStub(hostname, Integer.parseInt(port));
            workerpool.setWorkerConnection(workerId, workerServerStub);
        }
        HeartBeatResponse response = HeartBeatResponse.newBuilder()
                .setAcknowledge(true).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
