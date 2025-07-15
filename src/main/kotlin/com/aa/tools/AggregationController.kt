package com.aa.tools

import com.aa.service.CalculationAggregatorService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/aggregation")
class AggregationController(private val aggregatorService: CalculationAggregatorService) {

    @GetMapping("/total")
    fun getTotalSum(): Map<String, Any> {
        return mapOf(
            "totalSum" to aggregatorService.getTotalSum(),
            "operationCount" to aggregatorService.getOperationCount()
        )
    }

    @PostMapping("/reset")
    fun resetAggregation(): Map<String, String> {
        aggregatorService.resetAggregation()
        return mapOf("message" to "Aggregation counters reset successfully")
    }
}