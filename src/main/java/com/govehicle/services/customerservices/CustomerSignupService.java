package com.govehicle.services.customerservices;


import com.govehicle.entities.Customers.Customer;
import com.govehicle.repositories.customersrepositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class CustomerSignupService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        ts.setNanos(0);
        ts.setTime(0);
        System.out.println("ts = : "+ts);
        customer.setRegistrationDate(new Date());
        Customer savedCustomer = customerRepository.save(customer);
        return  savedCustomer;
    }

    public List<Customer> findCustomerByEmail(String email){

        return  customerRepository.findByEmail(email);
    } ;

}
