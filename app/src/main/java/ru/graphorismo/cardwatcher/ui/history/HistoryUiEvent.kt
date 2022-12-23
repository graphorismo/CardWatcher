package ru.graphorismo.cardwatcher.ui.history

sealed interface HistoryUiEvent{
    object ClickBackButton : HistoryUiEvent
    object ClickClearButton: HistoryUiEvent
    data class ClickHistoryLine(val line: String) : HistoryUiEvent {}
}