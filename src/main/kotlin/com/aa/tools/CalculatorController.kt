package com.aa.tools

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/calculator")
class CalculatorController(private val calculator: Calculator) {

    @PostMapping("/add")
    fun add(@RequestBody request: CalculationRequest): CalculationResponse {
        val result = calculator.add(request.a, request.b)
        return CalculationResponse(result)
    }

    @PostMapping("/subtract")
    fun subtract(@RequestBody request: CalculationRequest): CalculationResponse {
        val result = calculator.subtract(request.a, request.b)
        return CalculationResponse(result)
    }

    @PostMapping("/multiply")
    fun multiply(@RequestBody request: CalculationRequest): MultiplyResponse {
        val result = calculator.multiply(request.a, request.b)
        return MultiplyResponse(result)
    }
}