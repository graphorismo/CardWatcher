package ru.graphorismo.cardwatcher.domain.card

data class CardNumberData(
    val length: Int,
    val luhn: Boolean
)