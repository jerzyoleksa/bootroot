package org.example.module3.repository;

import org.example.module3.domain.ReportStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReportStatusRepository extends JpaRepository<ReportStatus, UUID> {

    @Modifying
    @Query(value="UPDATE ReportStatus r SET r.status = :status, r.downloadUrl = :url, r.updatedAt = CURRENT_TIMESTAMP " +
           "WHERE r.id = :id",
            nativeQuery = true)
    void updateStatus(UUID id, String status, String url);
}