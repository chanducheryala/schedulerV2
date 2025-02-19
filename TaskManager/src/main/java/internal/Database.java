package internal;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Database {

    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    private static final int SCAN_INTERVAL_SECONDS = 10;
    private static final String CONNECTION_STRING = "mongodb://root:scheduler@localhost:27017/";
    private static final String DATABASE_NAME = "scheduler";
    private static final String COLLECTION_NAME = "tasks";

    private MongoCollection<Document> collection;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final Workerpool workerpool = new Workerpool();
    private MongoClient mongoClient;

    public void connectToDB() {
        try {
            logger.info("Connecting to database");
            mongoClient = MongoClients.create(new ConnectionString(CONNECTION_STRING));
            MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
            collection = database.getCollection(COLLECTION_NAME);
            logger.info("Database connection successful.");
        } catch (Exception e) {
            logger.error("Error while connecting to MongoDB", e);
        }
    }

    public void startPeriodicScan() {
        logger.info("Starting periodic database scan every {} seconds.", SCAN_INTERVAL_SECONDS);
        scheduler.scheduleAtFixedRate(this::scanDatabase, SCAN_INTERVAL_SECONDS, SCAN_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }

    private void scanDatabase() {
        try {
            LocalDateTime now = LocalDateTime.now();
            Date mongoDate = Date.from(now.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date mongoDateTime = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

            logger.debug("Scanning database for tasks with date: {} and time <= {}", mongoDate, mongoDateTime);

            Bson filter = Filters.and(
                    Filters.eq("date", mongoDate),
                    Filters.lte("time", mongoDateTime),
                    Filters.eq("status", "PENDING")
            );

            List<Document> tasks = collection.find(filter).into(new ArrayList<>());
            logger.info("Found {} pending tasks.", tasks.size());

            tasks.forEach(this::processTask);
        } catch (Exception ex) {
            logger.error("Error while scanning database", ex);
        }
    }

    private void processTask(Document task) {
        try {
            ObjectId taskId = task.getObjectId("_id");
            logger.info("Processing task: {}", task.toJson());

            updateTaskStatus(taskId, "QUEUED");
            workerpool.sendTaskToWorker(taskId.toHexString(), task.toJson());
        } catch (Exception ex) {
            logger.error("Error processing task: {}", task.toJson(), ex);
        }
    }

    private void updateTaskStatus(ObjectId taskId, String status) {
        try (ClientSession session = mongoClient.startSession()) {
            session.startTransaction();
            Bson filter = Filters.eq("_id", taskId);
            Bson update = Updates.set("status", status);
            UpdateResult result = collection.updateOne(session, filter, update);

            if (result.getMatchedCount() > 0) {
                logger.info("Task {} status updated to {}", taskId, status);
                session.commitTransaction();
            } else {
                logger.warn("No task found with ID {} to update.", taskId);
                session.abortTransaction();
            }
        } catch (Exception ex) {
            logger.error("Error while updating task status", ex);
        }
    }

    public void stopPeriodicScan() {
        logger.info("Stopping periodic database scan.");
        scheduler.shutdown();
    }
}
