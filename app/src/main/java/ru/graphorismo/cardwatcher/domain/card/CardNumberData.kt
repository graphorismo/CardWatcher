package ru.graphorismo.cardwatcher.domain.card

data class CardNumberData(
    val length: Int? = null,
    val luhn: Boolean? = null
)