package com.aa.service

import com.aa.model.CityInfo
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducerService(private val kafkaTemplate: KafkaTemplate<String, Any>) {

    private val topicName = "city-info-0-out"

    fun sendCityInfo(cityInfo: CityInfo) {
        kafkaTemplate.send(topicName, cityInfo)
        println("Sent message to $topicName: $cityInfo")
    }
}
