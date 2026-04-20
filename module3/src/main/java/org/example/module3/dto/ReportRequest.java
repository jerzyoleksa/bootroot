package org.example.module3.dto;

import java.util.Map;
import java.util.UUID;

public record ReportRequest(
    Long userId,                    // kto składa żądanie
    String type,                    // np. "monthly-sales", "user-activity", "invoice-summary"
    Map<String, Object> params      // elastyczne parametry, np. {"startDate": "2025-01-01", "endDate": "2025-12-31"}
) {
    // Można dodać walidację @Valid jeśli chcesz (np. @NotNull userId, @NotBlank type)
}