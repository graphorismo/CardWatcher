package ru.graphorismo.cardwatcher.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDAO {
    @Query("SELECT * FROM HistoryLine")
    suspend fun getAllHistoryLines(): List<HistoryLine>

    @Insert
    suspend fun insertHistoryLine(historyLine: HistoryLine)

    @Query("DELETE FROM HistoryLine")
    suspend fun removeAll()
}