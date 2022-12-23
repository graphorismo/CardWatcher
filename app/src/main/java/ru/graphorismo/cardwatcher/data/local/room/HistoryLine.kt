package ru.graphorismo.cardwatcher.data.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
class HistoryLine(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    val line: String
) {

}