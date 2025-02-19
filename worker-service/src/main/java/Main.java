import internal.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args)  {

        /* grpc server for task manager calls*/
        WorkerGrpcServer workerGrpcServer = new WorkerGrpcServer();
        workerGrpcServer.startGrpcServer();

        TaskManagerStub taskManagerStub = new TaskManagerStub();
        taskManagerStub.connectToTaskManagerGrpcServer();

        WorkerPool workerPool = new WorkerPool();
        workerPool.startWorkerPool();

        WorkerHeartBeat workerHeartBeat = new WorkerHeartBeat();
        Thread.startVirtualThread(workerHeartBeat.periodicHeartBeat);
    }
}
