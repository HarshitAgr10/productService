package dev.harshit.productservice;

import dev.harshit.productservice.models.Category;
import dev.harshit.productservice.models.Product;
import dev.harshit.productservice.repositories.CategoryRepository;
import dev.harshit.productservice.repositories.ProductRepository;
import dev.harshit.productservice.repositories.projections.ProductWithTitleAndId;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class ProductServiceapr24ApplicationTests {

    @Autowired   // Tells Spring to inject the object of product repository
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void testingQuery() {
        Product product = productRepository.
                getProductWithASpecificTitleAndId("electronics", 4l);

        System.out.println(product.getTitle());
    }

    @Test
    public void testingQuery2() {
        ProductWithTitleAndId product = productRepository.
                getProductWithASpecificTitleAndId2("iPhone13", 4L);

        System.out.println(product.getId());
        System.out.println(product.getTitle());
        System.out.println(product.getDescription());
    }

    @Test
    @Transactional
    public void testingFetchTypes() {
        // By default, fetch type is lazy
        Category category = categoryRepository.findByTitle("electronics");

        // System.out.println(category.getTitle());  // Only 1 query will be generated -> Non-Collection type
        System.out.println(category.getProducts());  // Two separate queries will be generated -> Collection type

    }

    @Test
    public void testingFetchTypes2() {
        Category category = categoryRepository.findByTitle("electronics");

        System.out.println(category.getTitle());
    }

    @Test
    public void testingFetchTypes3() {
        Optional<Category> category = categoryRepository.findById(4L);

        System.out.println(category.get().getTitle());
    }

}

// Alternative way to test Queries written using HQL

// To get access to ProductRepository, use Dependency Injection by Spring

// @Autowired is used to automatically inject dependencies into beans. When Spring encounters a bean that needs
// dependency, it looks for bean of corresponding type in its application context and injects it into dependent bean.
// @Autowired can be used with constructor too, but it is optional to use

// @Test is used to mark a method as a Test method. When tests are run, JUnit will look for methods annotated
// with @Test and execute them to verify that code is behaving as expected.