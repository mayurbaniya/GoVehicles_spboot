package com.govehicle.controller.mailcontrollers;

import com.govehicle.entities.Customers.Customer;
import com.govehicle.entities.mail.MailStructure;
import com.govehicle.services.customerservices.CustomerSignupService;
import com.govehicle.services.mailservice.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MailController {

    private Customer customer;

    @Autowired
    private MailService mailService;
    @Autowired
    private CustomerSignupService customerSignupService;

    @RequestMapping(path = "/getdata", method = RequestMethod.POST)
    public ResponseEntity<String> sendMail(@RequestBody Customer customer) {

        //saving data in global user variable
        this.customer = customer;
        //mail
        String mail = customer.getEmail();
        System.out.println("received Mail: "+mail);

        List<Customer> customerExist = customerSignupService.findCustomerByEmail(mail);

        if (!customerExist.isEmpty())
            return new ResponseEntity<>(customerExist.get(0).getName(), HttpStatus.FOUND);
        else{
            try {
                // sending otp
                mailService.sendMail(mail);
                return new ResponseEntity<>(null, HttpStatus.OK);
            } catch (InvalidMailException e) {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("/verify/{otp}")
    public ResponseEntity<Customer> verifyOtpController(@PathVariable String otp){

        int i = mailService.verifyOtp(otp);

        if(i ==0){
            customerSignupService.saveCustomer(this.customer);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
    }

}

//        if(this.customer.getEmail() == null)
//            System.out.println("abhishek misa randika bacha");
//            System.out.println("chut magga kahika");
//            System.out.println("fck its working ");
//        else
//            System.out.println("it doesn't work");
//            System.out.println("jaa na randike indent pe v nai chlta");