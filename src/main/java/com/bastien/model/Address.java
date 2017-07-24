package com.bastien.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by bastien on 13/07/2017.
 */
@Data
@AllArgsConstructor
public class Address {
    @Id
    private String id;

    private final Integer number;
    private final String street;
    private final String city;

    //Constructor for jackson HTTP converter
    private Address() {
        number = null;
        street = null;
        city = null;
    }

    public Address(Integer number, String street, String city) {
        this.number = number;
        this.street = street;
        this.city = city;
    }
}

