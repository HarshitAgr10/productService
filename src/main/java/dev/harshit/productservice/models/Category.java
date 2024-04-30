package dev.harshit.productservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category extends BaseModel {

    // public Long id;

    private String title;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
    private List<Product> products;
}


// mappedBy attribute is used in JPA to establish bidirectional relationships between entities.
// By setting mappedBy, it is indicated that other side of relationship is responsible for managing association

// CascadeType.REMOVE setting specifies that when an operation to remove an entity is performed on owning
// entity, same operation should be cascaded to its associated entities, causing them to be removed as well

// @JsonIgnore is typically used in Spring applications with Jackson, JSON processing library, to ignore
// certain fields during serialization and deserialization of JSON objects

// 2 fetch types :- LAZY & EAGER
// By default, fetch type is LAZY

// @Fetch is used to customize fetching strategy for association between entities

// 3 Fetch Modes :- JOIN, SELECT, SUBSELECT
// By default, fetch mode is JOIN