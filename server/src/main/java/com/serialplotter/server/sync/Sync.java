package com.serialplotter.server.sync;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="syncs")
public class Sync {
    @Id
    @SequenceGenerator(
            name="sync_sequence",
            sequenceName = "sync_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "sync_sequence"
    )

    private Long syncId;
    private Long userId;
    private Long streamId;
    private String operation;
    private LocalDateTime syncRequestDate;
    private String status;

    @PreUpdate
    public void onUpdate() {
        syncRequestDate = LocalDateTime.now();
    }

    public Sync() {

    }

    public Sync( Long userId, Long streamId, String operation, String status) {
        this.userId = userId;
        this.streamId = streamId;
        this.operation = operation;
        this.status = status;
    }

    public Sync( Long userId, Long streamId, String operation, LocalDateTime syncRequestDate, String status) {
        this.userId = userId;
        this.streamId = streamId;
        this.operation = operation;
        this.syncRequestDate = syncRequestDate;
        this.status = status;
    }

    public Long getSyncId() {
        return syncId;
    }

    public void setSyncId(Long syncId) {
        this.syncId = syncId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStreamId() {
        return streamId;
    }

    public void setStreamId(Long streamId) {
        this.streamId = streamId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public LocalDateTime getSyncRequestDate() {
        return syncRequestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
