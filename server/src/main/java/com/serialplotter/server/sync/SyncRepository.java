package com.serialplotter.server.sync;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SyncRepository extends JpaRepository<Sync, Long> {
    List<Sync> findSyncJobsByUserId(Long userId);
}
