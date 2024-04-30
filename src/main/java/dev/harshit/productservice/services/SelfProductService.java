package dev.harshit.productservice.services;

import dev.harshit.productservice.dtos.GetCategoryDto;
import dev.harshit.productservice.models.Category;
import dev.harshit.productservice.models.Product;
import dev.harshit.productservice.repositories.CategoryRepository;
import dev.harshit.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long id) {
        return productRepository.findByIdIs(id) ;
    }

    @Override
    public Product createProduct(String title, String description, String image, String categoryTitle, double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);

        Category categoryFromDatabase = categoryRepository.findByTitle(categoryTitle);

        // If category not found in database, create a new category
        if (categoryFromDatabase == null) {
            Category newCategory = new Category();
            newCategory.setTitle(categoryTitle);
            categoryFromDatabase = newCategory;
            // categoryFromDatabase = categoryRepository.save(newCategory);
        }

        // If category was found from DB, then no new category will be created
        // If category is not found in the DB, a new category will be created because of cascade persist type
        product.setCategory(categoryFromDatabase);

        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts()  {
        return productRepository.findAll();
    }

    @Override
    public List<String> getAllCategories() {
        List<Category> allCategories = categoryRepository.findAll();  // Get all Categories and add to List

        // Since return type is String, Iterate over the List allCategories and store all elements to List categories
        List<String> categories = new ArrayList<>();
        for (Category category : allCategories) {
            categories.add(category.getTitle());
        }
        return categories;
    }

    @Override
    public List<Product> getSpecificCategory(String title) {
        Category categoryFromDatabase = categoryRepository.findByTitle(title);
        if (categoryFromDatabase != null) {
            return productRepository.findByCategory(categoryFromDatabase);
        }
        return new ArrayList<>();
    }

    @Override
    public Product updateProduct(Long id, String title, String description, String image, String categoryTitle, double price) {
        Product product = productRepository.findByIdIs(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setImageUrl(image);
        product.setPrice(price);

        Category categoryFromDatabase = categoryRepository.findByTitle(categoryTitle);
        if (categoryFromDatabase == null) {
            Category newCategory = new Category();
            newCategory.setTitle(categoryTitle);
            categoryFromDatabase = newCategory;
        }
        product.setCategory(categoryFromDatabase);

        return productRepository.save(product);
    }

    @Override
    public Product deleteProduct(Long id) {
        Product product = productRepository.findByIdIs(id);
        productRepository.deleteById(id);

        return product;
    }
}
