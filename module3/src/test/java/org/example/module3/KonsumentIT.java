package org.example.module3;

import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.example.module3.dto.ReportRequestedEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.ConsumerFactory;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

@SpringBootTest(classes = Module3Application.class)
class KonsumentIT {

    private static final String TOPIC = "test-topic";

//    @Autowired
//    private ConsumerFactory<String, String> consumerFactory;

    @Test
    void odczytuje_co_jest_w_topicu() {

        var props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "...");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "...");
        //props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        //props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        //props.put(com.fasterxml.jackson.databind.JsonDeserializer. ReportRequestedEvent.class.getName());


        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(List.of("test-topic"));

            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));

            if (records.isEmpty()) {
                System.out.println("Brak wiadomości w topicu");
                return;
            }

            records.forEach(record -> {
                System.out.println("Key: " + record.key());
                System.out.println("Value (String): " + record.value());
                System.out.println("Offset: " + record.offset());
            });

            // Przykład asercji
            String pierwszaWiadomosc = records.iterator().next().value();
            //assertThat(pierwszaWiadomosc).contains("test-");
        }
    }
}