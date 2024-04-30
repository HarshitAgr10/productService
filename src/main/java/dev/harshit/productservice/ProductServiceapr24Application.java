package dev.harshit.productservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceapr24Application {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceapr24Application.class, args);
    }

}


// Code architecture followed will be MVC
// First point of contact of APIs will be with Controller class
// Business logic will be with Service class
// To interact with database, class used will be Repository
// Object stored in the database will be Model
// DTOs are Objects whose purpose is to send data outside from inside and receive data from outside