package com.aa.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong

@Service
class CalculationAggregatorService(private val objectMapper: ObjectMapper) {

    private val logger = LoggerFactory.getLogger(CalculationAggregatorService::class.java)
    
    // Aggregated totals
    private val totalSum = AtomicLong(0)
    private val operationCount = AtomicLong(0)
    
    @KafkaListener(topics = ["calculator-results-0"], groupId = "calculator-consumer-group")
    fun consumeCalculationResult(message: String) {
        try {
            logger.info("Received message: $message")
            
            // Parse the JSON message
            val jsonNode = objectMapper.readTree(message)
            val operation = jsonNode.get("operation").asText()
            val operandA = jsonNode.get("operandA").asInt()
            val operandB = jsonNode.get("operandB").asInt()
            val result = jsonNode.get("result").asLong()
            
            // Aggregate all numbers (operands and results)
            val numbersSum = operandA + operandB + result
            totalSum.addAndGet(numbersSum)
            operationCount.incrementAndGet()
            
            logger.info("Operation: $operation, OperandA: $operandA, OperandB: $operandB, Result: $result")
            logger.info("Added $numbersSum to total. Current aggregated sum: ${totalSum.get()}, Operations processed: ${operationCount.get()}")
            
        } catch (e: Exception) {
            logger.error("Error processing calculation result message: $message", e)
        }
    }
    
    fun getTotalSum(): Long = totalSum.get()
    fun getOperationCount(): Long = operationCount.get()
    
    fun resetAggregation() {
        totalSum.set(0)
        operationCount.set(0)
        logger.info("Aggregation counters reset")
    }
}