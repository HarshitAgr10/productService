package dev.harshit.productservice.services;

import dev.harshit.productservice.models.Product;
import dev.harshit.productservice.repositories.ProductRepository;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SearchService {

    private ProductRepository productRepository;

    public SearchService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> search(String query, int pageNumber, int pageSize) {
        Sort sort = Sort.by("title").descending()
                .and(Sort.by("price").ascending());

        // List<String> sortValues = new ArrayList<>();
        // for (String sortValue : sortValues) {
        //     sort = Sort.by(sortValue).ascending();
        // }

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return productRepository.findByTitleContaining(query, pageable);
    }
}



// Page<> type typically holds a list of items representing the content of that page, along with metadata
// like total no. of items available, page number etc.

// PageRequest.of() is method used to create instances of PageRequest class, which implements Pageable interface

// Sort.by() is method used to create instances of Sort class, which represents sorting criteria for database
// queries. One or more fields can be specified to sort by, as well as direction of sorting (asc or desc)