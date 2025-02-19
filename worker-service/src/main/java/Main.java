import internal.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args)  {

        /* grpc server for task manager calls*/
        WorkerGrpcServer workerGrpcServer = new WorkerGrpcServer();
        workerGrpcServer.start();

        logger.info("connect to task manager grpc sever");
        TaskManagerStub taskManagerStub = new TaskManagerStub();
        taskManagerStub.connectToTaskManagerGrpcServer();

        WorkerPool workerPool = new WorkerPool();
        workerPool.startWorkerPool();

        WorkerHeartBeat workerHeartBeat = new WorkerHeartBeat(taskManagerStub);
        workerHeartBeat.periodicHeartBeat();
    }
}
