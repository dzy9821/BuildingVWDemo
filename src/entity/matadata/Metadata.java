package entity.matadata;

import java.util.UUID;

public class Metadata {
    private final String formatVersion = "2.0";
    private String UUID;
    private String creationTime;
    private Producer producer;
    private Task task;
    public Metadata(){
        this.UUID = String.valueOf(java.util.UUID.randomUUID());
    }

    public String getFormatVersion() {
        return formatVersion;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
