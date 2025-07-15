package com.aa.service

import com.aa.tools.CalculationResult
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducerService(private val kafkaTemplate: KafkaTemplate<String, Any>) {

    private val logger = LoggerFactory.getLogger(KafkaProducerService::class.java)
    private val topicName = "calculator-results-0"

    fun sendCalculationResult(result: CalculationResult) {
        try {
            kafkaTemplate.send(topicName, result)
            logger.info("Sent calculation result to Kafka: $result")
        } catch (e: Exception) {
            logger.error("Failed to send calculation result to Kafka", e)
        }
    }
}