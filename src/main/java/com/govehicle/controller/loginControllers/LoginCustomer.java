package com.govehicle.controller.loginControllers;

import com.govehicle.entities.Customers.Customer;
import com.govehicle.services.customerservices.CustomerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginCustomer {

    @Autowired
    private CustomerLoginService customerLoginService;

    @PostMapping("/logincustomer")
    public ResponseEntity<Customer> customerLogin(@RequestBody Customer customer){

        Customer cust = customerLoginService.getCustomer(customer.getEmail(), customer.getPassword());

            if(cust == null){
                return ResponseEntity.notFound().build();
            }else
                return ResponseEntity.ok(cust);
    }
}
