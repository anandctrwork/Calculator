package com.aa.tools

import org.springframework.stereotype.Service

@Service
class Calculator {

    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun subtract(a: Int, b: Int): Int {
        return a - b
    }

    fun multiply(a: Int, b: Int): Long {
        return a.toLong() * b
    }
}