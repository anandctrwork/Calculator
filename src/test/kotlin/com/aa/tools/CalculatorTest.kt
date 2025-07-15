package com.aa.tools

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CalculatorTest {

    @Test
    fun testAdd() {
        val calculator = Calculator()
        assertEquals(8, calculator.add(3, 5))
    }

    @Test
    fun testSubtract() {
        val calculator = Calculator()
        assertEquals(2, calculator.subtract(5, 3))
    }

    @Test
    fun testMultiply() {
        val calculator = Calculator()
        assertEquals(15L, calculator.multiply(3, 5))
    }
}