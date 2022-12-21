package ru.graphorismo.cardwatcher.domain.card

data class CardData(
    val bank: BankData,
    val brand: String,
    val country: CountryData,
    val number: CardNumberData,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)