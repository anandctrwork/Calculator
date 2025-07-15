package com.aa.tools

import com.aa.service.KafkaProducerService
import org.springframework.stereotype.Service

@Service
class Calculator(private val kafkaProducerService: KafkaProducerService) {

    fun add(a: Int, b: Int): Int {
        val result = a + b
        kafkaProducerService.sendCalculationResult(
            CalculationResult("ADD", a, b, result)
        )
        return result
    }

    fun subtract(a: Int, b: Int): Int {
        val result = a - b
        kafkaProducerService.sendCalculationResult(
            CalculationResult("SUBTRACT", a, b, result)
        )
        return result
    }

    fun multiply(a: Int, b: Int): Long {
        val result = a.toLong() * b
        kafkaProducerService.sendCalculationResult(
            CalculationResult("MULTIPLY", a, b, result)
        )
        return result
    }
}