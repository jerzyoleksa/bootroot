package org.example.module3.dto;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public record ReportRequestedEvent(
    UUID reportId,
    Long userId,
    String reportType,
    Map<String, Object> parameters,
    Instant requestedAt
) {}