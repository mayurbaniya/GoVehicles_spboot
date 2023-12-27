package com.govehicle.services.customerservices;

import com.govehicle.entities.Customers.Customer;
import com.govehicle.repositories.customersrepositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerLoginService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomer(String email, String password){

        List<Customer>  customer = customerRepository.findByEmailAndPassword(email, password);
        if(customer.isEmpty()){
            return null;
        }
        return customer.get(0);
    }

}
