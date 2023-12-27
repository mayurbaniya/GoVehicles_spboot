package com.govehicle.repositories.customersrepositories;

import com.govehicle.entities.Customers.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    List<Customer> findByEmail(String name);

    List<Customer> findByEmailAndPassword(String email, String password);

}
