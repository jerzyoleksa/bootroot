package org.example.module3.service;

import org.example.module3.dto.ReportRequestedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class ReportGeneratorService {

    private static final Logger log = LoggerFactory.getLogger(ReportGeneratorService.class);

    /**
     * Symuluje generowanie raportu – w rzeczywistości tu będzie prawdziwa logika.
     * Zwraca URL do pobrania gotowego raportu.
     */
    public String generateReport(ReportRequestedEvent event) {
        UUID reportId = event.reportId();
        log.info("Rozpoczynam generowanie raportu dla ID: {}", reportId);

        try {
            // Symulacja długiej operacji (np. 5–15 sekund)
            Thread.sleep(5000 + new Random().nextInt(10000)); // 5–15 sekund

            // Tutaj prawdziwa logika generowania:
            // - zapytanie do bazy danych
            // - stworzenie PDF/Excel/HTML
            // - zapis pliku lokalnie / na S3 / w bazie
            // - wygenerowanie publicznego URL-a

            String fakeDownloadUrl = "https://example.com/reports/" + reportId + ".pdf";

            log.info("Raport wygenerowany pomyślnie: {}", fakeDownloadUrl);
            return fakeDownloadUrl;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Przerwano generowanie raportu {}", reportId, e);
            throw new RuntimeException("Generowanie raportu przerwane", e);
        } catch (Exception e) {
            log.error("Błąd podczas generowania raportu {}: {}", reportId, e.getMessage(), e);
            throw new RuntimeException("Nie udało się wygenerować raportu", e);
        }
    }
}