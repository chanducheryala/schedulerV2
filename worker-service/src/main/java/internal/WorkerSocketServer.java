package internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;

public class WorkerSocketServer {
    private static final Logger logger = LoggerFactory.getLogger(WorkerSocketServer.class);

    private static ServerSocket serverSocket;
    private static String serverPort;
    private String hostname;


    public WorkerSocketServer() {
        this.hostname = "127.0.0.1";
    }

    public static void start() throws IOException {
        if(serverPort == null || serverPort.isEmpty()) {
            // find an available port
            serverSocket = new ServerSocket(0);
            serverPort = String.valueOf(serverSocket.getLocalPort());
        } else {
            int port = Integer.parseInt(serverPort.replace(":", ""));
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("127.0.0.1", port));
        }
        logger.info("worker server is running on port {}", serverPort);
    }

    public String getServerPort() {
        return serverPort;
    }

    public String getHostname() {
        return this.hostname;
    }
}
