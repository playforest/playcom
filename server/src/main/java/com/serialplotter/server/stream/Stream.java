package com.serialplotter.server.stream;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="streams")
public class Stream {
    @Id
    @SequenceGenerator(
            name="streams_sequence",
            sequenceName = "streams_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "streams_sequence"
    )
    private Long streamId;
    private String streamName;
    private String port;
    private int baudRate;
    private String filePath;

    private Long createdByUserId;
    private LocalDateTime createdDate;
    @PrePersist
    public void onCreate() {
        createdDate = LocalDateTime.now();
    }
    private LocalDateTime lastUpdated;
    @PreUpdate
    public void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }
    private Boolean isDeleted;

    public Stream() {

    }

    public Stream(String streamName, String port, int baudRate, String filePath, Long createdByUserId, LocalDateTime lastUpdated, Boolean isDeleted) {
        this.streamName = streamName;
        this.port = port;
        this.baudRate = baudRate;
        this.filePath = filePath;
        this.createdByUserId = createdByUserId;
        this.lastUpdated = lastUpdated;
        this.isDeleted = isDeleted;
    }

    public Stream(Long streamId, String streamName, String port, int baudRate, String filePath, Long createdByUserId, LocalDateTime lastUpdated, Boolean isDeleted) {
        this.streamId = streamId;
        this.streamName = streamName;
        this.port = port;
        this.baudRate = baudRate;
        this.filePath = filePath;
        this.createdByUserId = createdByUserId;
        this.lastUpdated = lastUpdated;
        this.isDeleted = isDeleted;
    }

    public Long getStreamId() {
        return streamId;
    }

    public void setStreamId(Long streamId) {
        this.streamId = streamId;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(int baudRate) {
        this.baudRate = baudRate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "Stream{" +
                "streamId=" + streamId +
                ", streamName='" + streamName + '\'' +
                ", port='" + port + '\'' +
                ", baudRate=" + baudRate +
                ", filePath='" + filePath + '\'' +
                ", createdByUserId=" + createdByUserId +
                ", createdDate=" + createdDate +
                ", lastUpdated=" + lastUpdated +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
