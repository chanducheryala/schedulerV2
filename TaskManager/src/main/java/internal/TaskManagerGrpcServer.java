package internal;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TaskManagerGrpcServer {
    private static final Logger logger = LoggerFactory.getLogger(TaskManagerGrpcServer.class);
    private static final int TASK_MANAGER_GRPC_SERVER_PORT = 9000;

    public void start() throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(TASK_MANAGER_GRPC_SERVER_PORT).addService(new TaskManagerService()).build();
        server.start();
        logger.info("gRpc server running at {}", TASK_MANAGER_GRPC_SERVER_PORT);
        server.awaitTermination();
    }
}
