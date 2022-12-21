package ru.graphorismo.cardwatcher.data.remote.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.graphorismo.cardwatcher.data.remote.retrofit.binlist.Binlist

interface IBinlistService {
    @GET("https://lookup.binlist.net/{cardBIN}")
    fun listRepos(@Path("cardNumber") cardBIN: String): Call<Binlist>?
}