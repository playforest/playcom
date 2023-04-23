package com.serialplotter.server.stream;

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
    private Long baudRate;
    private String filePath;
    private Long createdByUserId;
    private LocalDate createdDate;
    private LocalDateTime lastUpdated;
    @PreUpdate
    public void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }
    private Boolean isDeleted;

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

    public Long getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(Long baudRate) {
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

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
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
