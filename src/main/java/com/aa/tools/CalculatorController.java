package com.aa.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    @Autowired
    private Calculator calculator;

    @PostMapping("/add")
    public CalculationResponse add(@RequestBody CalculationRequest request) {
        int result = calculator.add(request.getA(), request.getB());
        return new CalculationResponse(result);
    }

    @PostMapping("/subtract")
    public CalculationResponse subtract(@RequestBody CalculationRequest request) {
        int result = calculator.subtract(request.getA(), request.getB());
        return new CalculationResponse(result);
    }

    @PostMapping("/multiply")
    public MultiplyResponse multiply(@RequestBody CalculationRequest request) {
        long result = calculator.multiply(request.getA(), request.getB());
        return new MultiplyResponse(result);
    }
}