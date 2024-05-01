package dev.harshit.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseModel {

    // private Long id;

    private String title;

    private String description;

    private double price;

    private String imageUrl;

    private int quantity;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Category category;

}

// lombok library provides annotations(@Getter/@Setter) that helps to generate getter and setter methods automatically
// lombok annotations(@AllArgsConstructor/@NoArgsConstructor) automatically generates constructor

// @Entity in JPA represents a table in a relational database, and instances(objects) of a Class
// having Entity annotation correspond to rows in that table

// private Long id is being inherited from parent class BaseModel

// @ManyToOne in JPA is used to establish a many-to-one relationship between two entities

// CascadeType.PERSIST setting specifies that when an operation to persist entity is performed on owning
// entity, same operation should be cascaded to its associated entities, causing them to be persisted as well.