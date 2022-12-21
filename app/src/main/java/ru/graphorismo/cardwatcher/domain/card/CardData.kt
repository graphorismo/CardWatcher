package ru.graphorismo.cardwatcher.domain.card

data class CardData(
    val bank: BankData? = null,
    val brand: String? = null,
    val country: CountryData? = null,
    val number: CardNumberData? = null,
    val prepaid: Boolean? = null,
    val scheme: String? = null,
    val type: String? = null
)