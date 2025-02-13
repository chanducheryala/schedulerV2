package internal;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.concurrent.*;

public class Coordinator {
    private static final Logger logger = LoggerFactory.getLogger(Coordinator.class);

    public void start() {
        try {
            ExecutorService executor = Executors.newFixedThreadPool(2);
            executor.submit(this::connectToDatabase);
            executor.submit(this::scanDatabase);

        } catch (Exception ex) {
            logger.error("error {}",  ex.getStackTrace());
        }
    }

    public void connectToDatabase() {
        try {
            logger.info("Connecting to database");
            ConnectionString connectionString = new ConnectionString("mongodb://root:scheduler@localhost:27017/");
            MongoClient mongoClient = MongoClients.create(connectionString);
            logger.info("Database connection succesfull");
        } catch (Exception e) {
            logger.error("error while connecting to mongoDB {}", e.getStackTrace());
        }
    }

    public void manageWorkerPool() {
        try {
           // every periodic time, we will recieve heartbeats of  WorkerServer
        } catch (Exception ex) {

        }
    }

    final Runnable getCurrentTimeTasks = new Runnable() {
        @Override
        public void run() {
            logger.info("current date and time is {}", LocalDateTime.now());
        }
    };

    public void scanDatabase() {
        try {
            logger.info("Scanning the Database");
            ScheduledExecutorService ticker = Executors.newScheduledThreadPool(1);
            ticker.scheduleAtFixedRate(getCurrentTimeTasks, 0, 5, TimeUnit.SECONDS);
        } catch (Exception ex) {
            logger.error("error {}", ex.getStackTrace());
        }
    }
}
