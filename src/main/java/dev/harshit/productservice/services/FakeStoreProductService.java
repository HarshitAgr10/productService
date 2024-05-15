package dev.harshit.productservice.services;

import dev.harshit.productservice.dtos.FakeStoreProductDto;
import dev.harshit.productservice.dtos.GetCategoryDto;
import dev.harshit.productservice.models.Category;
import dev.harshit.productservice.models.Product;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreService")
public class FakeStoreProductService implements ProductService {

    private RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate,
                                   RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Product getSingleProduct(Long id) {

//        FakeStoreProductDto fakeStoreProductDto = restTemplate
//                .getForObject("https://fakestoreapi.com/products/" + id,
//                        FakeStoreProductDto.class);

        Product productFromCache = (Product) redisTemplate.opsForValue().get(String.valueOf(id));
        // If productFromCache != null( already present in redis), return the product, no need to call 3rd party API below
        if (productFromCache != null) {
            return productFromCache;
        }

        // Using ResponseEntity instead of above part to get entire response like body, header and status code
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.
                getForEntity("https://fakestoreapi.com/products/" + id,
                        FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        // Store the product in redis, if product not found before
        redisTemplate.opsForValue().set(String.valueOf(id), product);

        return product;
    }

    @Override
    public Product createProduct(String title, String description, String image, String category, double price) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);

        FakeStoreProductDto response = restTemplate
                .postForObject("https://fakestoreapi.com/products",
                        fakeStoreProductDto, FakeStoreProductDto.class);

        return response.toProduct();

    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] response = restTemplate
                .getForObject("https://fakestoreapi.com/products",
                        FakeStoreProductDto[].class);

        // Iterate over the list response and convert that into list of products
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto: response) {
            products.add(fakeStoreProductDto.toProduct());
        }

        return products;
    }

    @Override
    // public List<GetCategoryDto> getAllCategories() {  // When using GetCategoryDto
    public List<String> getAllCategories() {
        String[] response = restTemplate
                .getForObject("https://fakestoreapi.com/products/categories",
                        String[].class);

        List<String> getCategories = new ArrayList<>();
        for (String category: response) {
            //getCategories.add(new GetCategoryDto(category));
            getCategories.add(category);
        }

        return getCategories;
    }

    @Override
    public List<Product> getSpecificCategory(String title) {
        FakeStoreProductDto[] response = restTemplate
                .getForObject("https://fakestoreapi.com/products/category/" + title,
                        FakeStoreProductDto[].class);

        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto: response) {
            products.add(fakeStoreProductDto.toProduct());
        }
        return products;
    }

    @Override
    public Product updateProduct(Long id, String title, String description, String image, String category, double price) {
        String url = "https://fakestoreapi.com/products/{id}";

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setDescription(description);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);

        HttpEntity<FakeStoreProductDto> requestEntity = new HttpEntity<>(fakeStoreProductDto);
        ResponseEntity<FakeStoreProductDto>  responseEntity = restTemplate
                .exchange(url, HttpMethod.PUT, requestEntity, FakeStoreProductDto.class, id);

        return responseEntity.getBody().toProduct();
    }

    @Override
    public Product deleteProduct(Long id) {
        String url = "https://fakestoreapi.com/products/{id}";

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate
                .exchange(url, HttpMethod.DELETE, null, FakeStoreProductDto.class, id);

        return responseEntity.getBody().toProduct();
    }

}

// Annotation Component to indicate that class is a Spring component, Component tells Spring to
// auto-detect these classes for dependency injection and create an object of class FakeStoreProductService

// @Service is specialization of @Component except it also indicates particular class is service class

// RestTemplate is library that simplifies HTTP requests and consuming Restfull web services.
// Provides method for performing HTTP operations (GET,POST,PUT etc.) and handles conversion of
// response bodies(JSON) to Java objects using message converters(Jackson).

// Method getForObject() is used to make a GET request to specified URL and deserialize
// response body(JSON) into a Java object of specified type

// FakeStoreProductDto.class is informing rest template to make a call to given url and whatever
// json it is getting, convert that json into this class and return the data in form of its object of class

// To avoid creating Rest Template object everytime it is required, use constructor for dependency injection
// Spring will create Application context where it creates and store one object per class inside it

// .postForObject method in Spring's RestTemplate class is used to make a POST request to specified URL,
// and it expects to receive request in form of object of specified type.
// Invoke method by passing (URL to which POST request will be made, object to be sent in request
// body(UserDTO), expected response type(UserDTO))

// Use Array instead of Arraylist because array is a normal class, no type erasure(<type>) or any
// other such problem found with arrays like arraylist

// .getForEntity is used to make a GET request to a specified URL and receive the entire HTTP
// response, including status code, headers and body as an instance of ResponseEntity

// HttpEntity is class that represents an HTTP request or response entity, including headers and body

// ResponseEntity is class that represents an HTTP response entity, including status code, headers and body

// exchange method is used to perform an HTTP request and receive an HTTP response with full control
// over request and response details. It allows to specify various parameters such as URL parameters,
// request method, request entity, expected response type, uri variables.

// opsForValue() :- Method provided by RedisTemplate in Spring Data Redis framework. It returns an instance
// of valueOperations, which is an interface for working with Redis string values.
