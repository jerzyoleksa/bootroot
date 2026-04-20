package org.example.module3.util;  // lub: common.dto / api.response / payload

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public record ApiResponse<T>(
        boolean success,
        String message,          // komunikat dla użytkownika (często po polsku / w języku aplikacji)
        T data,
        ErrorDetails error
) {
    // Fabryki – bardzo lubiane, bo czytelne i chainable
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, null, data, null);
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(true, message, data, null);
    }

    public static <T> ApiResponse<T> error(String message, int status) {
        return new ApiResponse<>(false, message, null, new ErrorDetails(message, status, null, null));
    }

    public static <T> ApiResponse<T> error(String message, int status, String code) {
        return new ApiResponse<>(false, message, null, new ErrorDetails(message, status, code, null));
    }

    // Wersja z dodatkowymi szczegółami (walidacja, multi-error)
    public static <T> ApiResponse<T> error(String message, int status, String code, List<String> details) {
        return new ApiResponse<>(false, message, null, new ErrorDetails(message, status, code, details));
    }
}