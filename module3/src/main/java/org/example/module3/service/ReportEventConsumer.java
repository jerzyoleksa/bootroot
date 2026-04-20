package org.example.module3.service;

import org.example.module3.dto.ReportRequestedEvent;
import org.example.module3.repository.ReportStatusRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReportEventConsumer {

    private final ReportGeneratorService reportGeneratorService;
    private final ReportStatusRepository statusRepo;
    private static final Logger log = LoggerFactory.getLogger(ReportGeneratorService.class);

    public ReportEventConsumer(
            ReportGeneratorService reportGeneratorService,
            ReportStatusRepository statusRepo
    ) {
        this.reportGeneratorService = reportGeneratorService;
        this.statusRepo = statusRepo;
    }

    @KafkaListener(
            topics = "report-requests",
            groupId = "report-processors"
    )
    @KafkaListener(topics = "report-requests", groupId = "report-processors")
    public void processReportRequest(@Payload ReportRequestedEvent event) {
        // ^^^ dodaj @Payload przed parametrem
        log.info("Odebrano event: {}", event.reportId());

        UUID reportId = event.reportId();

        try {
            updateStatus(reportId, "PROCESSING", null);

            String downloadUrl = reportGeneratorService.generateReport(event);

            updateStatus(reportId, "COMPLETED", downloadUrl);
        } catch (Exception e) {
            updateStatus(reportId, "FAILED", null);
            System.err.println("Błąd przetwarzania raportu " + reportId + ": " + e.getMessage());
        }
    }

    private void updateStatus(UUID reportId, String status, String downloadUrl) {
        statusRepo.findById(reportId).ifPresent(rs -> {
            rs.setStatus(status);
            rs.setDownloadUrl(downloadUrl);
            statusRepo.save(rs);
        });
    }
}