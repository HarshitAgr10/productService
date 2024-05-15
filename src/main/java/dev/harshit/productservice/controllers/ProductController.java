package dev.harshit.productservice.controllers;

import dev.harshit.productservice.dtos.CreateProductRequestDto;
import dev.harshit.productservice.dtos.ErrorDto;
import dev.harshit.productservice.dtos.FakeStoreProductDto;
import dev.harshit.productservice.dtos.GetCategoryDto;
import dev.harshit.productservice.models.Product;
import dev.harshit.productservice.services.FakeStoreProductService;
import dev.harshit.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    // ProductService productService = new FakeStoreProductService();

    ProductService productService;

    public ProductController(@Qualifier("fakeStoreService") ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto productRequestDto) {

        return productService.createProduct(
                productRequestDto.getTitle(),
                productRequestDto.getDescription(),
                productRequestDto.getImage(),
                productRequestDto.getCategory(),
                productRequestDto.getPrice()
        );
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> responseData = productService.getAllProducts();

        // Creating own ResponseEntity of Product, and passing response data and specific status code (202 here)
        ResponseEntity<List<Product>> responseEntity =
                new ResponseEntity<>(responseData, HttpStatusCode.valueOf(202));

        return responseEntity;
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return productService.getSingleProduct(id);
    }


//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ErrorDto> handleNullPointerException(){
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setMessage("Something went wrong. Please try again");
//
//        return new ResponseEntity<>(errorDto, HttpStatusCode.valueOf(404));
//    }

    @GetMapping("/products/categories")
    // public List<GetCategoryDto> getAllCategories() {
    public List<String> getAllCategories() {
        return productService.getAllCategories();
    }

    @GetMapping("/products/categories/{title}")
    public List<Product> getSpecificCategory(@PathVariable("title") String title) {
        return productService.getSpecificCategory(title);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id,
                                     @RequestBody CreateProductRequestDto productDto) {
        return productService.updateProduct(id,
                productDto.getTitle(),
                productDto.getDescription(),
                productDto.getImage(),
                productDto.getCategory(),
                productDto.getPrice()
        );
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) {
        return productService.deleteProduct(id);
    }

}

// Controller class specific to Product (Get, update, delete, read products)
// Annotation RestController to inform Spring to create an object for class ProductController
// Annotations GetMapping, PostMapping to inform Spring what these methods are supposed to do
// Class is nothing but a key-value pair (key :- variable name, value :- variable value)

// Jackson :- Famous library that takes java object and converts it into a json and vice-versa.

// Object of ProductService is created to use the business logic, not the best way
// Instead of us creating object everytime,create constructor & use Spring annotations @Component/@Service on classes

// For any particular class, Spring creates Application context where it creates and stores one object per class inside it.
// Anytime it is needed to inject that object, it will pick from application context and use it as required automatically

// getProductById is returning Product class, no need to create DTO because Product doesn't contain any
// sensitive information, entire Product model can be returned

// @RequestBody is used in controller methods to extract the request body and convert it into Java object

// ResponseEntity contains everything that a response requires: Data, Status Code and Headers

// Instead of simply returning list of product, create a ResponseEntity and return to get entire response
// like responseData, status code, header also

// @ExceptionHandler is used to handle exceptions thrown by request mapping methods within a controller
// It allows to define a method within the controller that will handle a specific type of exception

// How to handle Exception Way 1:- e.g. If NullPointerException is found in any of the method, call
// handleNullPointerException method to handle error instead of Spring handling it directly by itself

// @DeleteMapping is used to handle HTTP DELETE requests sent to a specific URL pattern

// @Qualifier is used to disambiguate beans when multiple beans of the same types are available
// @Primary is used to indicate a Primary bean(By default) when multiple beans of same type are defined
