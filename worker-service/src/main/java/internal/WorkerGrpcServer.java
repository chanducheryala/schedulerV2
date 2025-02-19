package internal;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerGrpcServer {
    private static final Logger logger = LoggerFactory.getLogger(WorkerGrpcServer.class);

    public WorkerGrpcServer() {};

    public void startGrpcServer() {
       try {
           Server workerGrpcSever = ServerBuilder
                   .forPort(0)
                   .addService(new WorkerService())
                   .build();
           workerGrpcSever.start();
           logger.info("Worker Grpc is running on : {}", workerGrpcSever.getPort());
           workerGrpcSever.awaitTermination();
       } catch (Exception exception) {
           logger.error("error while running worker grpc server");
       }
    }
}
