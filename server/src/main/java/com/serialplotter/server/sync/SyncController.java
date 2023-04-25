package com.serialplotter.server.sync;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="api/v1/sync")
public class SyncController {

    private final SyncService syncService;

    @Autowired
    public SyncController(SyncService syncService) {
        this.syncService = syncService;
    }

    @GetMapping(path="/{userId}/{streamId}")
    public String getData(@PathVariable Long userId, @PathVariable Long streamId) {
        return syncService.getData(userId, streamId);
    }

    @PostMapping(path="/{userId}/{streamId}")
    public void  updateData(@PathVariable Long userId, @PathVariable Long streamId) {

    }

    @DeleteMapping(path="/{userId}/{streamId}")
    public void deleteData(@PathVariable Long userId, @PathVariable Long streamId) {

    }

}
