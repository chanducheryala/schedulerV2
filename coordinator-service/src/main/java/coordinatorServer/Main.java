package coordinatorServer;

import coordinatorServer.coorinator.CoordinatorService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9000).addService(new CoordinatorService()).build();
        server.start();
        logger.info("gRpc server running at 9090");
        server.awaitTermination();
    }
}