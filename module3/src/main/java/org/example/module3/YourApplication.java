package org.example.module3;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YourApplication {

    public static void main(String[] args) {
        SpringApplication.run(YourApplication.class, args);
    }

    @PostConstruct
    void checkH2() {
        try {
            Class.forName("org.h2.Driver");
            System.out.println("H2 Driver jest na classpath ✅");
        } catch (ClassNotFoundException e) {
            System.err.println("Brak H2 Drivera na classpath ❌");
        }
    }
}