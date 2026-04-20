package org.example.module3.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class SlowService {

    // ────────────────────────────────────────────────
    // Wersja BEZ cache – za każdym razem długo trwa
    // ────────────────────────────────────────────────
    public String getExpensiveDataWithoutCache(String param) {
        Instant start = Instant.now();

        simulateSlowOperation(2500); // 2,5 sekundy

        Instant end = Instant.now();
        long millis = Duration.between(start, end).toMillis();

        return "Bez cache → wynik dla '" + param + "' (czas: " + millis + " ms)";
    }

    // ────────────────────────────────────────────────
    // Wersja Z cache – pierwszy raz długo, potem błyskawicznie
    // ────────────────────────────────────────────────
    @Cacheable(value = "expensiveData", key = "#param")
    public String getExpensiveDataWithCache(String param) {
        Instant start = Instant.now();

        System.out.println("Czekam...");
        simulateSlowOperation(2500); // 2,5 sekundy tylko za pierwszym razem

        Instant end = Instant.now();
        long millis = Duration.between(start, end).toMillis();

        return "jakis tam wynik, sprawdz w logach, jak nie ma Czekam... to znaczy ze wzielo z Cachea";
    }

    // ────────────────────────────────────────────────
    // Symulacja długiego zadania (np. zapytanie do bazy / API / obliczenia)
    // ────────────────────────────────────────────────
    private void simulateSlowOperation(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}