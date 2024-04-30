package dev.harshit.productservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;

    private Date updatedAt;

    private boolean isDeleted;
}

// Base Model contains Columns which every database would definitely contain

// @Id in JPA is used to mark a field in Java class as Primary Key of an entity
// @MappedSuperClass is used to define superclass whose mappings are applied to entities that inherit
// from it. It allows to define common fields and mappings that are shared among multiple entity classes.

// @GeneratedValue is used to specify strategy for generating primary key values for entities