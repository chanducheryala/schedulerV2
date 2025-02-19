import internal.Database;
import internal.TaskManagerGrpcServer;
import internal.Workerpool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException, InterruptedException {
        TaskManagerGrpcServer grpcServer = new TaskManagerGrpcServer();
        grpcServer.start();

        Workerpool workerpool = new Workerpool();
        workerpool.manageWorkerpool();

        Database database = new Database();
        database.connectToDB();
        database.startPeriodicScan();
    }
}


/*
*          TaskManager Service will
* */