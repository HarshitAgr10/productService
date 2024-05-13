package dev.harshit.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {

    private String query;

    private int pageNumber;

    private int pageSize;

    private String sortValue;
}

// SearchRequestDto contains parameters which frontend needs to send to search api
