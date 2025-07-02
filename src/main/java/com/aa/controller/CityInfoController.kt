package com.aa.controller

import com.aa.model.CityInfo
import com.aa.service.KafkaProducerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cityinfo")
class CityInfoController(private val kafkaProducerService: KafkaProducerService) {

    @PostMapping("/publish")
    fun publishCityInfo(@RequestBody cityInfo: CityInfo) {
        kafkaProducerService.sendCityInfo(cityInfo)
    }
}
