package org.example.module3.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "report_status")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportStatus {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String status;          // PENDING, PROCESSING, COMPLETED, FAILED

    private String downloadUrl;     // null dopóki nie wygenerowany

    private Instant createdAt;

    private Instant updatedAt;

    public ReportStatus(UUID reportId, String pending, Instant o) {

        this.id = reportId;
        this.status = pending;
        this.createdAt = o;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}