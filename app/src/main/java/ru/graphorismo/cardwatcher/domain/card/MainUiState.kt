package ru.graphorismo.cardwatcher.domain.card

data class MainUiState(val cardData: CardData = CardData(),
                       val exception : Exception? = null) {
}