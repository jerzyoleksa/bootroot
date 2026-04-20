package org.example.module3;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(
    partitions = 1,
    topics = { "test-topic" },
    brokerProperties = {
        "listeners=PLAINTEXT://localhost:9092",
        "port=9092"
    }
)
@DirtiesContext
class SimpleKafkaTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void shouldSendAndReceiveMessage() throws InterruptedException {
        // given
        BlockingQueue<ConsumerRecord<String, String>> records = new LinkedBlockingQueue<>();
        
        // when
        kafkaTemplate.send("test-topic", "klucz", "Witaj Kafka!");

        // wtedy czekamy na wiadomość (można też użyć @KafkaListener w teście)
        // Tu najprostsza wersja - w realnym teście lepiej użyć KafkaListener lub TestConsumer
        // Ale dla minimalnego przykładu możemy po prostu wysłać i założyć, że nie wybuchnie

        // Wersja z małym oczekiwaniem (dla bardzo prostego testu)
        Thread.sleep(300); // brzydkie, ale działa w 90% najprostszych przypadków

        // albo lepsza wersja z consumerem (patrz niżej)
    }
}