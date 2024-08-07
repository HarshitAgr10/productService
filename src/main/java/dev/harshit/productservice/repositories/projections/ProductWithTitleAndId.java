package dev.harshit.productservice.repositories.projections;

// Something like DTOs except to get data from DB and our app
public interface ProductWithTitleAndId {
    // Put getter method for corresponding attributes

    Long getId();

    String getTitle();

    String getDescription();
}


// Projections allow to define interfaces or classes with specific getters to retrieve only required fields
// from entities instead of fetching the entire entity.

// Superset projection allows to retrieve a subset of fields from entity.