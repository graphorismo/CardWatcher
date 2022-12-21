package ru.graphorismo.cardwatcher.data.remote

import ru.graphorismo.cardwatcher.data.remote.retrofit.IBinlistService
import ru.graphorismo.cardwatcher.domain.IRemoteRepository
import ru.graphorismo.cardwatcher.domain.card.CardData

class RemoteDataRepository(binListService: IBinlistService) : IRemoteRepository {
    override suspend fun getCardDataByBIN(bin: String): CardData {
        TODO("Not yet implemented")
    }
}