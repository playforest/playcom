package com.serialplotter.server.sync;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="api/v1/sync")
public class SyncController {

    private final SyncService syncService;

    @Autowired
    public SyncController(SyncService syncService) {
        this.syncService = syncService;
    }

    @GetMapping(path = "/job/{userId}")
    public List<Sync> getSyncJobs(@PathVariable Long userId) {
        return syncService.getSyncJobsByUser(userId);
    }

    @GetMapping(path="/data/{userId}/{streamId}")
    public String getData(@PathVariable Long userId, @PathVariable Long streamId) {
        return syncService.getData(userId, streamId);
    }

    @PostMapping(path="/data/{userId}")
    public void  updateData(@PathVariable Long userId, @RequestBody Map<String, Object> payload) {

        Sync sync = new Sync(userId,
                            ((Number) payload.get("streamId")).longValue(),
                            (String) payload.get("syncType"),
                            "PENDING" );

        if (userId != null && sync.getStreamId() != null
                && sync.getStatus() != null && payload.get("data") != null) {
            syncService.putData(userId, sync, (String) payload.get("data"));

        }
    }

    @DeleteMapping(path="/{userId}/{streamId}")
    public void deleteData(@PathVariable Long userId, @PathVariable Long streamId) {

    }

}
