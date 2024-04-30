package dev.harshit.productservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}


// Bean is nothing but a Java object that spring creates and puts it in the Application Context

// @Bean is used to indicate that a method in a Configuration class produces a bean to be
// managed by the Spring container. When Spring scans application for components, it
// identifies @Bean-annotated methods and registers return value of those methods as Beans.

// @Configuration is used to declare that class provides configuration to Spring Application Context.
// Classes annotated with @Configuration are typically used to define beans using @Bean methods