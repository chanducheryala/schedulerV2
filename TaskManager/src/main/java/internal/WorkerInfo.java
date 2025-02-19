package internal;

public class WorkerInfo {
    private long workerId;
    private String host;
    private String port;

    public WorkerInfo(long workerId, String host, String port) {
        this.workerId = workerId;
        this.host = host;
        this.port = port;
    }

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
