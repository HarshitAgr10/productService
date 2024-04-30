package dev.harshit.productservice.repositories;

import dev.harshit.productservice.dtos.GetCategoryDto;
import dev.harshit.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByTitle(String title);

    Category save(Category category);

    Optional<Category> findById(Long id);

    List<Category> findAll();
}


// findByTitle in Spring Data JPA will automatically generate appropriate SQL query to select all title
// where title field matches provided parameter value, find title and also return a list with specified title