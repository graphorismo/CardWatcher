package ru.graphorismo.cardwatcher.data.local

import ru.graphorismo.cardwatcher.data.local.room.HistoryDatabase
import ru.graphorismo.cardwatcher.data.local.room.HistoryLine

class LocalHistoryRepository(private val historyDatabase: HistoryDatabase)
    : ILocalHistoryRepository {
    override suspend fun getHistory(): List<String> {
        val dao = historyDatabase.historyDao()
        val historyLines = dao.getAllHistoryLines()
        return historyLines.map { it.line }
    }

    override suspend fun addToHistory(line: String) {
        val dao = historyDatabase.historyDao()
        val historyLine = HistoryLine(line = line)
        dao.insertHistoryLine(historyLine)
    }
}