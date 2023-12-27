package com.govehicle.controller.SignUpControllers;

import com.govehicle.entities.Customers.Customer;
import com.govehicle.services.customerservices.CustomerSignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SignUpController {


    // ************* RELOCATED THIS METHOD TO MainController class ******************** //
    // ************* verifyOtp method of  mailController ********************* //

//    @Autowired
//    private CustomerSignupService customerSignupService;
//
//    @RequestMapping(path = "/signupp", method = RequestMethod.POST)
//    public ResponseEntity<Customer> createUser(@RequestBody Customer customer){
//        System.out.println("signup method called");
//
//        String email = customer.getEmail();
//        List<Customer> customerFound = customerSignupService.findCustomerByEmail(email);
//
//        if(!customerFound.isEmpty()){
//            return new ResponseEntity<>(customerFound.get(0), HttpStatus.FOUND);
//        }
//        else {
//        Customer savedCustomer = customerSignupService.saveCustomer(customer);
//
//        System.out.println(savedCustomer);
//        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
//
//        }
//
//
//
//    }


}
