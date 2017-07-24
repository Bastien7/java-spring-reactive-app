package com.bastien.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * Created by bastien on 13/07/2017.
 */
@Data
@AllArgsConstructor
@Getter
public class Person {
    @Id
    private String id;

    private final String name;
    private Integer age;
    private Address address;

    //We have to assign dummy value to the name in order to have a default constructor
    public Person() {
        this.name = "";
    }

    public Person(String name, Integer age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
