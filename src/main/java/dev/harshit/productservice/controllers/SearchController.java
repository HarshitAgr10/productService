package dev.harshit.productservice.controllers;

import dev.harshit.productservice.dtos.SearchRequestDto;
import dev.harshit.productservice.models.Product;
import dev.harshit.productservice.services.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchController {

    private SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/search")
    public Page<Product> search(@RequestBody SearchRequestDto searchRequestDto) {
        return searchService.search(searchRequestDto.getQuery(),
                searchRequestDto.getPageNumber(),
                searchRequestDto.getPageSize());
    }
}
