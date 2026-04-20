package org.example.module3.web;

import jakarta.persistence.Cacheable;
import jakarta.transaction.Transactional;
import org.example.module3.domain.ReportStatus;
import org.example.module3.dto.ReportRequest;
import org.example.module3.dto.ReportRequestedEvent;
import org.example.module3.repository.ReportStatusRepository;
import org.example.module3.service.SlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;


@RestController
public class ReportController {


    private final KafkaTemplate<String, ReportRequestedEvent> kafkaTemplate;


    private final ReportStatusRepository statusRepo;

    private final SlowService slowService;

    public ReportController(KafkaTemplate<String, ReportRequestedEvent> kafkaTemplate, ReportStatusRepository statusRepo, SlowService slowService) {
        this.kafkaTemplate = kafkaTemplate;
        this.statusRepo = statusRepo;
        this.slowService = slowService;
    }


    @GetMapping("/generate")
    @Transactional
    public ResponseEntity<?> generateReport() {
        UUID reportId = UUID.randomUUID();

        // Zapisujemy wstępny status
        statusRepo.save(new ReportStatus(reportId, "PENDING", null));

        // Wysyłamy event asynchronicznie
        ReportRequestedEvent event = new ReportRequestedEvent(
                reportId, new Long(1), "type1", new HashMap<>(), null
        );

        kafkaTemplate.send("report-requests", reportId.toString(), event);

        // Natychmiastowa odpowiedź
        return ResponseEntity.accepted()
                .header("Location", "/api/reports/" + reportId + "/status")
                .build();
    }




    @GetMapping("/slow/{param}")
    public String withoutCache(@PathVariable String param) {
        return slowService.getExpensiveDataWithoutCache(param);
    }

    @GetMapping("/fast/{param}")
    public String withCache(@PathVariable String param) {
        return slowService.getExpensiveDataWithCache(param);
    }
}
