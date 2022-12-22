package ru.graphorismo.cardwatcher.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.graphorismo.cardwatcher.data.remote.RemoteDataRepository
import ru.graphorismo.cardwatcher.data.remote.retrofit.IBinlistService
import ru.graphorismo.cardwatcher.data.remote.IRemoteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesIRemoteRepository(binlistService: IBinlistService) : IRemoteRepository {
        return RemoteDataRepository(binlistService);
    }

    @Singleton
    @Provides
    fun providesIBinlistService(retrofit: Retrofit) : IBinlistService{
        return retrofit.create(IBinlistService::class.java);
    }

    @Singleton
    @Provides
    fun providesRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://lookup.binlist.net/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build();
    }


}