package ru.graphorismo.cardwatcher.domain

import ru.graphorismo.cardwatcher.domain.card.CardData

interface IRemoteRepository {

    suspend fun getCardDataByBIN(bin: String): CardData
}