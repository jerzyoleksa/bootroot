package org.example.module3.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugKafkaController {

    @Autowired(required = false)
    private KafkaTemplate<String, Object> kafkaTemplate;

    @GetMapping("/debug-kafka")
    public String debug() {
        return kafkaTemplate != null ? "KafkaTemplate OK" : "KafkaTemplate NULL - auto-config nie działa";
    }
}