package org.example.module3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Module3Application.class, properties = "spring.main.allow-bean-definition-overriding=true")
@EmbeddedKafka(
        partitions = 1,
        topics = "moj-temat-testowy"
//        ,brokerProperties = {
//                "listeners=PLAINTEXT://localhost:9092",
//                "port=9092"
//        }
)
@DirtiesContext
class ProstyTestWysylOdbiorZKonsola {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final CountDownLatch LATCH = new CountDownLatch(1);
    private static volatile String odebranaWiadomosc;

    @KafkaListener(
            topics = "moj-temat-testowy",
            groupId = "test-grupa-konsola"
    )
    void sluchajIWypisz(String wiadomosc) {
        System.out.println("\n╔════════════════════════════════════════════╗");
        System.out.println("║       Otrzymano wiadomość z Kafka !        ║");
        System.out.println("║ Wartość : " + wiadomosc);
        System.out.println("╚════════════════════════════════════════════╝\n");

        odebranaWiadomosc = wiadomosc;
        LATCH.countDown();
    }

    @Test
    void powinien_wyslac_i_odebrac_wiadomosc() throws Exception {
        String wiadomosc = "Cześć Kafka! Test z " + System.currentTimeMillis();

        System.out.println("\nWysyłam: " + wiadomosc + "\n");

        // Wysyłamy i czekamy na potwierdzenie dostarczenia
        kafkaTemplate.send("moj-temat-testowy", wiadomosc)
                .get(5, TimeUnit.SECONDS);

        System.out.println("Wysłano → czekam max 12 sekund na listener...\n");

        boolean otrzymano = LATCH.await(12, TimeUnit.SECONDS);

        assertTrue(otrzymano, "Listener nie otrzymał wiadomości w ciągu 12 sekund!");

        assertEquals(wiadomosc, odebranaWiadomosc, "Odebrana wiadomość nie zgadza się");

        System.out.println("✓ Sukces – wiadomość dotarła ✓");
    }
}