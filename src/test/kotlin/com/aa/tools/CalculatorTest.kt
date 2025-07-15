package com.aa.tools

import com.aa.service.KafkaProducerService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock

class CalculatorTest {

    private val mockKafkaProducerService = mock<KafkaProducerService>()
    private val calculator = Calculator(mockKafkaProducerService)

    @Test
    fun testAdd() {
        assertEquals(8, calculator.add(3, 5))
    }

    @Test
    fun testSubtract() {
        assertEquals(2, calculator.subtract(5, 3))
    }

    @Test
    fun testMultiply() {
        assertEquals(15L, calculator.multiply(3, 5))
    }
}