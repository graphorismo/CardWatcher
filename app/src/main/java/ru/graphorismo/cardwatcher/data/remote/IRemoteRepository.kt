package ru.graphorismo.cardwatcher.data.remote

import ru.graphorismo.cardwatcher.domain.card.CardData

interface IRemoteRepository {

    suspend fun getCardDataByBIN(bin: String): CardData
}