package org.example.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "persons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"address"})   // unikamy cyklicznego toString jeśli jest relacja
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 80)
    private String lastName;

    @Column(unique = true, nullable = false, length = 120)
    private String email;

    @Column(unique = true, length = 11)
    private String pesel;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Gender gender;

    @Column(precision = 12, scale = 2)
    private BigDecimal salary;

    @Column(nullable = false)
    private boolean active = true;

    @Column
    private LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;

    // Relacja z inną encją – przykład
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    // Przydatne do zapytań typu: findByAddressCity(...)
    public String getAddressCity() {
        return address != null ? address.getCity() : null;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}