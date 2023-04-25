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
    private String syncType;
    private LocalDateTime syncRequestDate;
    private String syncStatus;

    @PreUpdate
    public void onUpdate() {
        syncRequestDate = LocalDateTime.now();
    }

    public Sync() {

    }

    public Sync(Long syncId, Long userId, Long streamId, String syncType, LocalDateTime syncRequestDate) {
        this.syncId = syncId;
        this.userId = userId;
        this.streamId = streamId;
        this.syncType = syncType;
        this.syncRequestDate = syncRequestDate;
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

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }

    public LocalDateTime getSyncRequestDate() {
        return syncRequestDate;
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus;
    }
}
