package ru.graphorismo.cardwatcher.data.remote.retrofit.binlist

data class Binlist(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: CardNumber,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)