package ru.graphorismo.cardwatcher.data.remote.retrofit.binlist

data class CardNumber(
    val length: Int,
    val luhn: Boolean
)