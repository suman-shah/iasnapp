package com.project75.ioeallsubjectnotes.model;
public class Topics {
    String topicName;
    String fileName;

    public Topics(String topicName, String fileName) {
        this.topicName = topicName;
        this.fileName = fileName;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getFileName() {
        return fileName;
    }
}
