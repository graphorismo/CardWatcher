package ru.graphorismo.cardwatcher.data.local

interface ILocalHistoryRepository {

    suspend fun getHistory() : List<String>
    suspend fun addToHistory(line: String)
    suspend fun clearHistory()
}