package internal;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WorkerGrpcServer {
    private static final Logger logger = LoggerFactory.getLogger(WorkerGrpcServer.class);
    private final Server server;

    public WorkerGrpcServer() {
        this.server = ServerBuilder
                .forPort(0)
                .addService(new WorkerService())
                .build();
    }

    public void start() {
        try {
            server.start();
            logger.info("Worker gRPC server is running");
            Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
        } catch (IOException e) {
            logger.error("Error while starting Worker gRPC server", e);
        }
    }

    public void stop() {
        if (server != null) {
            server.shutdown();
            logger.info("Worker gRPC server stopped.");
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
