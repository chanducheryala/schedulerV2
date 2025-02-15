import grpc.CoordinatorServiceGrpc;
import internal.WorkerServer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();

        CoordinatorServiceGrpc.CoordinatorServiceBlockingStub stub = CoordinatorServiceGrpc.newBlockingStub(channel);

        logger.info("stub info : {}", stub.getChannel().toString());
        WorkerServer workerServer = new WorkerServer(stub);
        workerServer.start();
    }
}
