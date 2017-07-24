package com.bastien.repository;

import com.bastien.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * Created by bastien on 13/07/2017.
 */
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {
    Flux<Person> findPersonByAge(final Integer age);
    Flux<Person> findPersonByAddressCity(final String city);
    Flux<Person> findPersonByAgeAndAddressCity(final Integer age, final String city);
}
