package org.example.module3.service;

import org.example.module3.domain.Person;
import org.example.module3.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class PersonService {

    //@Autowired - usuwam bo uzycie autowired wyklucza uzycie final, a final nam potrzebne by klasa byl Immutable
    private final PersonRepository personRepository;

    //czyli najlepsze wstrzykiwanie poprzez konstruktor i pole private final
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/person")
    public List<Person> findAll(){
        return personRepository.findAll();
    }
}
