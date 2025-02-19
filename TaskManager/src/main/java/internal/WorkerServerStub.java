package internal;

import grpc.WorkerServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerServerStub {
    private static final Logger logger = LoggerFactory.getLogger(WorkerServerStub.class);
    private final ManagedChannel channel;
    private final WorkerServiceGrpc.WorkerServiceBlockingStub stub;

    public WorkerServerStub(String host, int port) {
        this.channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();

        this.stub = WorkerServiceGrpc.newBlockingStub(channel);
        logger.info("WorkerServerStub initialized on port: {}", port);
    }

    public WorkerServiceGrpc.WorkerServiceBlockingStub getStub() {
        return stub;
    }

    public void shutdown() {
        logger.info("Shutting down gRPC channel");
        channel.shutdown();
    }
}
