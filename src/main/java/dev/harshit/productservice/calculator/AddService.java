package dev.harshit.productservice.calculator;

import org.springframework.stereotype.Service;

@Service
public class AddService {
    public int sumFromAddService(int a, int b) {
        System.out.println("Some logic from add service");
        System.out.println("Some logic from add service");
        int result = a + b;
        return result;
    }
}
