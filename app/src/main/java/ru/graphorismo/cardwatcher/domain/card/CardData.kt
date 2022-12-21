package ru.graphorismo.cardwatcher.domain.card

data class CardData(
    val bank: BankData = BankData(),
    val brand: String = "",
    val country: CountryData = CountryData(),
    val number: CardNumberData = CardNumberData(),
    val prepaid: Boolean? = null,
    val scheme: String = "",
    val type: String = ""
)