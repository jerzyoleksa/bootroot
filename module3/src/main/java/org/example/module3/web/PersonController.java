package org.example.module3.web;

import lombok.RequiredArgsConstructor;
import org.example.module3.domain.Person;
import org.example.module3.service.PersonService;
import org.example.module3.util.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RequiredArgsConstructor
@RestController
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/person")
    public ApiResponse<List<Person>> findAll() {

        //return ApiResponse.error("Invalid JSON format", 400);
        //return personService.findAll();
        return ApiResponse.success(personService.findAll());
    }

    @GetMapping("/")
    public String home() {
        return "Hello from Spring Boot!";
    }
}
