package dev.harshit.productservice.calculator;

import org.springframework.stereotype.Controller;

@Controller
public class CalculatorController {
    private AddService addService;

    public CalculatorController(AddService addService) {
        this.addService = addService;
    }

    public int add(int a, int b) {
        // Some random logic
        System.out.println("Some logic");
        int result = addService.sumFromAddService(a, b);
        System.out.println("Some other logic");

        return result;
    }
}
