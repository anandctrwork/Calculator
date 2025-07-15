package com.aa.tools

import java.time.LocalDateTime

data class CalculationResult(
    val operation: String,
    val operandA: Int,
    val operandB: Int,
    val result: Number,
    val timestamp: LocalDateTime = LocalDateTime.now()
)