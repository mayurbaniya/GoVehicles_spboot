package com.govehicle.entities.Customers;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String number;
    private String email;
    private String city;
    private String password;
    private String gender;
    private Date registrationDate;

    public int getId() {
        return id;
    }

    public Customer() {

    }


    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Customer(int id, String name, String number, String email, String city, String password, String gender, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
        this.city = city;
        this.password = password;
        this.gender = gender;
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", city='" + city + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", registrationDate=" + registrationDate +
                '}';
    }
}
