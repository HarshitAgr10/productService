package dev.harshit.productservice.dtos;

import dev.harshit.productservice.models.Category;
import dev.harshit.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    private Long id;

    private String title;

    private String image;

    private String description;

    private String category;

    private double price;

    public Product toProduct() {
        Product product = new Product();

        product.setId(getId());
        product.setTitle(getTitle());
        product.setDescription(getDescription());
        product.setImageUrl(getImage());

        Category category = new Category();
        category.setTitle(getCategory());
        product.setCategory(category);

        return product;
    }

}

// DTOs are Objects whose purpose is to send data outside from inside and receive data from outside
// DTOs are design pattern used to transfer data between different layers of an application
// such as between Controller and Service layer or between Service layer and repository
// Often used to encapsulate(required format) data fetched from a database or received from an external system

// Here, creating own FakeStoreProductDTO because being dependent on 3rd party API is not a good idea.
// Because someday 3rd party can change their data or user may need format different from 3rd party's format