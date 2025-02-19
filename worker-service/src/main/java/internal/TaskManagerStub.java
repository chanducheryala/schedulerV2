package internal;

import grpc.TaskManagerServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskManagerStub {
    private static final Logger logger = LoggerFactory.getLogger(TaskManagerStub.class);
    private static final String HOSTNAME = "127.0.0.1";
    private static final int TASK_MANAGER_PORT = 9000;

    private TaskManagerServiceGrpc.TaskManagerServiceBlockingStub stub;

    public TaskManagerStub() {};

    public void connectToTaskManagerGrpcServer () {
        try {
            ManagedChannel channel = ManagedChannelBuilder
                    .forAddress(HOSTNAME, TASK_MANAGER_PORT)
                    .usePlaintext()
                    .build();
            this.stub = TaskManagerServiceGrpc.newBlockingStub(channel);
            logger.info("stub : {}", this.stub);
        } catch (Exception ex) {
            logger.error("error while getting task manager stub {}", ex.getStackTrace());
        }
    }

    public TaskManagerServiceGrpc.TaskManagerServiceBlockingStub getStub() {
        return stub;
    }
}
