package dev.harshit.productservice.commons;

import dev.harshit.productservice.dtos.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationCommons {
    private RestTemplate restTemplate;

    public AuthenticationCommons(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token) {
        if (token == null) {
            return null;
        }

        // Create an HttpHeaders object and set the token in the Authorization header
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        // Create an HttpEntity object with the headers. This entity will be sent in the request.
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Use exchange method to make the request with headers
        ResponseEntity<UserDto> response = restTemplate.exchange(
                "http://UserService/users/validate",
                HttpMethod.POST,
                entity,
                UserDto.class
        );

        return response.getBody();   // Return the body of the response, which is a UserDto object
    }
}






/*
 * HttpHeaders:
 * Used to represent HTTP request and response headers.
 * It provides a convenient way to set, get, and manipulate HTTP headers in a Spring application.
*/

/*
 * HttpEntity:
 * Represents an HTTP request or response entity, which consists of headers and a body.
 * Used to pass request headers and body to a RestTemplate when making HTTP requests,
 * or to represent the HTTP response entity returned from a server.
*/
