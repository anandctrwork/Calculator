package com.aa.service

import com.aa.model.CityInfo
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.kafka.core.KafkaTemplate

@ExtendWith(MockitoExtension::class)
class KafkaProducerServiceTest {

    @Mock
    private lateinit var kafkaTemplate: KafkaTemplate<String, Any>

    @InjectMocks
    private lateinit var kafkaProducerService: KafkaProducerService

    @Test
    fun `should send city info to kafka topic`() {
        val cityInfo = CityInfo(id = 12345, zipCode = 75028, city = "Flower Mound", state = "TX")
        val topicName = "city-info-0-out"

        kafkaProducerService.sendCityInfo(cityInfo)

        verify(kafkaTemplate).send(topicName, cityInfo)
    }
}
