package org.example.module3;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

@Component   // ← tylko to! Żadnego @SpringBootTest, żadnego @TestConfiguration tutaj
public class TestKafkaListener {

    private final CountDownLatch latch = new CountDownLatch(1);
    private final List<String> received = new CopyOnWriteArrayList<>();

    @KafkaListener(
            topics = "test-topic",
            groupId = "test-group-${random.uuid}"
    )
    void onMessage(String message) {
        System.out.println("received message: " + message);
        received.add(message);
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public List<String> getReceivedMessages() {
        return received;
    }

    public void reset() {
        received.clear();
        // latch nie resetujemy – lepiej tworzyć nowy obiekt przy potrzebie
    }
}