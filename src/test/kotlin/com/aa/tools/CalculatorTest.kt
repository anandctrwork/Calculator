package com.aa.tools

import org.junit.Assert.assertTrue
import org.junit.Test

class CalculatorTest {

    @Test
    fun testAdd() {
        val cal = Calculator()
        assertTrue(cal.add(3, 5) == 8)
    }

    @Test
    fun testSubstract() {
        val cal = Calculator()
        assertTrue(cal.substract(5, 3) == 2)
    }

    @Test
    fun testMultiply() {
        val cal = Calculator()
        assertTrue(cal.multiply(3, 5) == 15L)
    }
}
