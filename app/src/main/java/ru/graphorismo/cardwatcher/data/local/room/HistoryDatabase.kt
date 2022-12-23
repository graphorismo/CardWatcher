package ru.graphorismo.cardwatcher.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ HistoryLine::class ], version=1)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao() : HistoryDAO
}