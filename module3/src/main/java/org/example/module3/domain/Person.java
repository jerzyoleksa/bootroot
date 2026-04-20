package org.example.module3.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String firstName;

    // Konstruktor używany w kodzie (bez ID!)
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean checkAge() {
        System.out.println("Checking age");
        return true;
    }
}
