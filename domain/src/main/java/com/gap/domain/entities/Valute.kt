package com.gap.domain.entities

data class Valute(
    val id: String,
    val charCode: String,
    val nominal: Int,
    val name: String,
    val value: Double,
    val previous: Double,
    val difference: Double
)



