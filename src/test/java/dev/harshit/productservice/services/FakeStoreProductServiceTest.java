package dev.harshit.productservice.services;

import dev.harshit.productservice.dtos.FakeStoreProductDto;
import dev.harshit.productservice.models.Product;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FakeStoreProductServiceTest {

    private RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

    private RedisTemplate redisTemplate = Mockito.mock(RedisTemplate.class);

    private ValueOperations valueOperations = Mockito.mock(ValueOperations.class);  // To mock OpsForValue()

    FakeStoreProductService fakeStoreProductService =
            new FakeStoreProductService(restTemplate, redisTemplate);


    // Testing getSingleProduct when data is present in cache(redisTemplate.opsForValue().get(String.valueOf(id))
    @Test
    public void testGetSingleProductWhenDataIsInCache() {
        Product testProduct = new Product();
        testProduct.setTitle("Test Product");

        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get("1")).thenReturn(testProduct);

        Product productFromCache = fakeStoreProductService.getSingleProduct(1L);

        assertEquals("Test Product", productFromCache.getTitle());
    }

    // Testing getSingleProduct when data is not present in cache
    @Test
    public void testGetSingleProductWhenDataIsNotInCache() {
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get("1")).thenReturn(null);

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle("Test Product");

        ResponseEntity<FakeStoreProductDto> responseEntity =
                new ResponseEntity<>(fakeStoreProductDto, HttpStatusCode.valueOf(200));

        when(restTemplate.getForEntity(
                "https://fakestoreapi.com/products/1", FakeStoreProductDto.class))
                .thenReturn(responseEntity);

        Product product = fakeStoreProductService.getSingleProduct(1L);

        assertEquals("Test Product", product.getTitle());

        verify(valueOperations, times(1)).set(any(), any());
    }

}






/* * verify:-
Used to check that certain interactions with your mock objects occurred. This is useful for ensuring that
methods were called with the expected arguments, the correct number of times, and in the right order.
* */

/* *
* verify: Mockito method used to check that a method was called on a mock object.
* valueOperations: Mock object whose method invocation is being verified.
* times(1): Specifies that the method should have been called exactly once.
* set(any(), any()): Specifies that the set method should have been called with any arguments. The any()
                     matchers indicate that any values of the respective parameter types are acceptable.
 * */