package org.example.repository;

import org.example.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository     // ← opcjonalne w 99% przypadków
public interface PersonRepository extends JpaRepository<Person, Long> {

    // Podstawowe wyszukiwanie
    Optional<Person> findByEmail(String email);
    Optional<Person> findByPesel(String pesel);

    // Kilka warunków
    List<Person> findByCityAndActiveTrue(String city);

    // Porównania
    List<Person> findByAgeGreaterThanEqual(int minAge);
    List<Person> findBySalaryBetween(BigDecimal min, BigDecimal max);

    // LIKE / zawieranie
    List<Person> findByLastNameContainingIgnoreCase(String fragment);

    // Sortowanie + limit
    List<Person> findTop5ByPointsDesc();

    // Istnienie / liczenie
    boolean existsByPhone(String phone);
    long countByCity(String city);

    // Usuwanie
    void deleteByEmail(String email);
}