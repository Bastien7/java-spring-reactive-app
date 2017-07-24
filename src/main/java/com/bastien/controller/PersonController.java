package com.bastien.controller;

import com.bastien.model.Person;
import com.bastien.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Optional;

/**
 * Created by bastien on 13/07/2017.
 */
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping("/find")
    public Flux<Person> findPerson(@RequestParam Optional<Integer> age, @RequestParam Optional<String> city) {
        return personService.find(age, city);
    }

    @GetMapping("/generateData")
    public Flux<Person> generateData() {
        return personService.generateData();
    }
}
