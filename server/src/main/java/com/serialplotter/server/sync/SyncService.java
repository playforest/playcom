package com.serialplotter.server.sync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyncService {
    private final S3SyncService s3service = new S3SyncService();
    private final SyncRepository syncRepository;

    @Autowired
    public SyncService(SyncRepository syncRepository) {
        this.syncRepository = syncRepository;
    }

    public List<Sync> getSyncJobs() {
        return syncRepository.findAll();
    }

    public List<Sync> getSyncJobsByUser(Long userId) {
        return syncRepository.findSyncJobsByUserId(userId);
    }

    public String getData(Long userId, Long streamId) {
        Sync latestSuccessfulSyncJob = syncRepository.findLatestSuccessfulSyncJob(streamId, "SUCCESS");
        String key = userId.toString() + "/" + streamId.toString() + "_" + latestSuccessfulSyncJob.getSyncId();

        return s3service.downloadObject(key);
    }

    public void putData(Long userId, Sync sync, String data) {
        Sync syncPending = syncRepository.save(sync);

        System.out.println(data);
        String key = userId.toString() + "/" + sync.getStreamId().toString() + "_" + syncPending.getSyncId().toString();
        boolean uploadSuccessful = s3service.writeObject(key, data);

        if (uploadSuccessful) {
            syncPending.setStatus("SUCCESS");
            syncRepository.save(syncPending);
        } else {
            syncPending.setStatus("FAILED");
            syncRepository.save(syncPending);
        }
    }


}
