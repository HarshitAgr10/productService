package dev.harshit.productservice.services;

import dev.harshit.productservice.dtos.GetCategoryDto;
import dev.harshit.productservice.models.Category;
import dev.harshit.productservice.models.Product;

import java.util.List;

public interface ProductService {

    public Product getSingleProduct(Long id);

    public Product createProduct(String title, String description,
                                 String image, String category, double price);

    public List<Product> getAllProducts();

    // public List<GetCategoryDto> getAllCategories();
    public List<String> getAllCategories();

    public List<Product> getSpecificCategory(String title);

    public Product updateProduct(Long id, String title, String description,
                                 String image, String category, double price);

    public Product deleteProduct(Long id);
}

// ProductService interface will have all methods describing the business logic

// Always try to keep Service more generic not very specific to some DTOs