package ru.graphorismo.cardwatcher.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.graphorismo.cardwatcher.data.local.ILocalHistoryRepository
import ru.graphorismo.cardwatcher.data.local.LocalHistoryRepository
import ru.graphorismo.cardwatcher.data.local.room.HistoryDatabase
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

    @Provides
    fun providesHistoryDatabase(@ApplicationContext context: Context) : HistoryDatabase{
        return Room.databaseBuilder(
                context.applicationContext,
                HistoryDatabase::class.java,
                "HistoryDB"
            ) .build()
    }

    @Provides
    fun providesILocalHistoryRepository(historyDatabase: HistoryDatabase): ILocalHistoryRepository{
        return LocalHistoryRepository(historyDatabase)
    }


}