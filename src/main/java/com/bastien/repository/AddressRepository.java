package com.bastien.repository;

import com.bastien.model.Address;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * Created by bastien on 13/07/2017.
 */
public interface AddressRepository extends ReactiveMongoRepository<Address, String> {
}
