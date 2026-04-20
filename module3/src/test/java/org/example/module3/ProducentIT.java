package org.example.module3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest(classes = Module3Application.class)
class ProducentIT {

    // nic więcej nie potrzeba – spring sam podłączy się do localhost:9092
    // (o ile masz w application.yml / properties: spring.kafka.bootstrap-servers=localhost:9092)
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    void wysylaWiadomosc() {

        kafkaTemplate.send("test-topic", "klucz", "Witaj Kafka!");

    }
}