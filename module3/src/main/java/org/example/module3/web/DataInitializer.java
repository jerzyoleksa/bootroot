package org.example.module3.web;

import org.example.module3.domain.Person;
import org.example.module3.domain.Product;
import org.example.module3.repository.PersonRepository;
import org.example.module3.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PersonRepository personRepository;

    private final ProductRepository productRepository;


    public DataInitializer(PersonRepository personRepository, ProductRepository productRepository) {
        this.personRepository = personRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Zapisujemy kilka osób
//        personRepository.save(new Person(new Long(28), "Jan", "Kowalski"));
//        personRepository.save(new Person(new Long(35), "Anna", "Nowak"));
//        personRepository.save(new Person(new Long(44), "Piotr", "Wiśniewski"));
          personRepository.save(new Person("Jan", "Kowalski"));
          personRepository.save(new Person("Anna", "Nowak"));
          personRepository.save(new Person("Piotr", "Wiśniewski"));

          personRepository.flush();

          productRepository.save(new Product(null, "monitor", "asd", "asd"));
          productRepository.save(new Product(null, "telewizor", "asd", "asd2"));
          productRepository.flush();

        // Odczytujemy wszystkie
        System.out.println("Wszystkie osoby w bazie:");
        personRepository.findAll().forEach(System.out::println);
    }
}