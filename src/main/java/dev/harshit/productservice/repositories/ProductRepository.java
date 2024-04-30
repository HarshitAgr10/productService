package dev.harshit.productservice.repositories;

import dev.harshit.productservice.models.Category;
import dev.harshit.productservice.models.Product;
import dev.harshit.productservice.repositories.projections.ProductWithTitleAndId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product save(Product entity);

    Product findByIdIs(Long id);

    // Way 1:- Writing methods and calling them
    List<Product> findAll();


    // Way 2 :- HQL -> Hibernate Query Language
    @Query("select p from Product p where p.category.title = :title and p.id = :id")
    Product getProductWithASpecificTitleAndId(@Param("title") String title, @Param("id") Long id);


    // Way 3:- Native SQL query (Inside " " is native SQL query not the HQL query)
    @Query(value = "select * from product where id = :id and title = :title", nativeQuery = true)
    Product getProductWithSomeTitleAndId(@Param("title") String title, @Param("id") Long id);

    // Way 2 :- HQL
    @Query("select p.id, p.title from Product p where p.title = :title and p.id = :id")
    ProductWithTitleAndId getProductWithASpecificTitleAndId2(@Param("title") String title, @Param("id") Long id);

    List<Product> findByCategory(Category category);

    void deleteById(Long id);
}


// Extending JpaRepository allows to create repository for specific entity type
// <Product, Long> -> Here Product is entity type and Long is type of Primary key

// findByIdIs allows to create query methods in repositories by simply declaring their method signature
// The Is keyword is typically used to create query method that filters entities based on an equality condition

// @Query allows to define custom JPQL(Java Persistence Query Language) or native SQL queries directly in repository interface.
// @Param is used to specify named parameters in JPQL or native SQL queries defined with @Query annotation.
// It allows to assign a name to method parameters used in custom query methods.