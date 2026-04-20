package org.example.module3.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorDetails(
        String message,
        int status,
        String code,               // np. "USER_NOT_FOUND", "INVALID_CREDENTIALS", "INSUFFICIENT_BALANCE"
        List<String> details       // np. pola walidacji: ["email: must be valid", "password: min 8 chars"]
) {}