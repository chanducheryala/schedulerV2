import grpc.CoordinatorServiceGrpc;
import grpc.HeartBeatRequest;
import grpc.HeartBeatResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();

        CoordinatorServiceGrpc.CoordinatorServiceBlockingStub stub = CoordinatorServiceGrpc.newBlockingStub(channel);

        HeartBeatRequest.Builder request = HeartBeatRequest.newBuilder().setWorkerId(1);

        HeartBeatResponse response = stub.sendHeartBeat(request.build());

        logger.info("response is  {}", response);

    }
}
