package com.serialplotter.server.sync;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface SyncRepository extends JpaRepository<Sync, Long> {
    List<Sync> findSyncJobsByUserId(Long userId);
    Sync findFirstByStreamIdAndStatusOrderBySyncIdDesc(Long streamId, String status);

    @Query(value = """
                    SELECT s FROM Sync s 
                    WHERE s.status = :status AND s.streamId = :streamId 
                    ORDER BY s.syncId DESC LIMIT 1
                    """)
    Sync findLatestSuccessfulSyncJob(Long streamId, String status);
}
