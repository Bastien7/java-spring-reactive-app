package com.bastien.service;

import com.bastien.model.Address;
import com.bastien.model.Person;
import com.bastien.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by bastien on 13/07/2017.
 */
@Component
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    private final List<String> cities = toLower(Arrays.asList("Luxembourg", "Metz", "Bettembourg"));
    private final List<String> streets = toLower(Arrays.asList("Royal street", "Station road"));


    /**
     * Find person depending of search arguments.
     * These arguments are Optional and so need to be passed.
     *
     * @param age It HAS to be not null
     * @param city It HAS to be not null
     */
    public Flux<Person> find(final Optional<Integer> age, final Optional<String> city) {
        Flux<Person> flux;
        Integer safeAge = age.orElse(null);
        String safeCity = city.map(s -> s.toLowerCase()).orElse(null);

        if(age.isPresent() && city.isPresent())
            flux = personRepository.findPersonByAgeAndAddressCity(safeAge, safeCity);
        else if(age.isPresent())
            flux = personRepository.findPersonByAge(safeAge);
        else if(city.isPresent())
            flux = personRepository.findPersonByAddressCity(safeCity);
        else
            flux = personRepository.findAll();

        return flux.filter(person -> person.getName() != null);
    }

    /**
     * Generate dummy data and return it.
     */
    public Flux<Person> generateData() {
        List<Person> personList = IntStream.of(10, 45, 23, 28, 34)
                .boxed()
                .map(age -> generatePerson(age))
                .collect(Collectors.toList());

        Mono<Void> deleteMono = personRepository.deleteAll();
        Flux<Person> personFlux = deleteMono.thenMany(personRepository.saveAll(personList)); //Here we chain a Flux to the deleteAll Mono

        return personFlux;
    }

    /**
     * Generate a single data.
     */
    private Person generatePerson(final Integer age) {
        Address address = new Address(age * 3, streets.get(age % streets.size()), cities.get(age % cities.size()));
        return new Person("dude" + age, age, address);
    }

    private static List<String> toLower(List<String> list) {
        return list.stream().map(String::toLowerCase).collect(Collectors.toList());
    }
}
