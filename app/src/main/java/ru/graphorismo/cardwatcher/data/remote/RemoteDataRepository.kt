package ru.graphorismo.cardwatcher.data.remote

import android.util.Log
import ru.graphorismo.cardwatcher.data.remote.exceptions.BlankBinException
import ru.graphorismo.cardwatcher.data.remote.exceptions.CardNotFoundException
import ru.graphorismo.cardwatcher.data.remote.exceptions.RequestTimeoutException
import ru.graphorismo.cardwatcher.data.remote.retrofit.IBinlistService
import ru.graphorismo.cardwatcher.data.remote.retrofit.binlist.Binlist
import ru.graphorismo.cardwatcher.domain.IRemoteRepository
import ru.graphorismo.cardwatcher.domain.card.BankData
import ru.graphorismo.cardwatcher.domain.card.CardData
import ru.graphorismo.cardwatcher.domain.card.CardNumberData
import ru.graphorismo.cardwatcher.domain.card.CountryData

class RemoteDataRepository(val binListService: IBinlistService) : IRemoteRepository {
    override suspend fun getCardDataByBIN(bin: String): CardData {
        if(bin.isBlank()) throw BlankBinException()
        val response = binListService.callForCardDataByItsBIN(bin)
        var cardData: CardData
        if (response != null) {
            if(response.isSuccessful && response.body() != null){
                cardData = convertBinlistToCardData(response.body()!!)
            }else{
                throw CardNotFoundException()
            }
        }else{
            throw RequestTimeoutException()
        }
        return cardData
    }

    private fun convertBinlistToCardData(binlist: Binlist): CardData{
        val bankData = BankData(
            city = binlist.bank.city,
            name = binlist.bank.name,
            phone = binlist.bank.phone,
            url = binlist.bank.url)

        val countryData = CountryData(
            latitude = binlist.country.latitude,
            longitude = binlist.country.longitude,
            name = binlist.country.name)

        val cardNumberData = CardNumberData(
            length = binlist.number.length,
            luhn = binlist.number.luhn)

        val cardData = CardData(
            bank = bankData,
            brand = binlist.brand,
            country = countryData,
            number = cardNumberData,
            prepaid = binlist.prepaid,
            scheme = binlist.scheme,
            type = binlist.type);

        return cardData;
    }
}