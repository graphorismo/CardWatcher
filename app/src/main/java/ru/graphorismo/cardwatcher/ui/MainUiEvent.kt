package ru.graphorismo.cardwatcher.ui

sealed interface MainUiEvent{
    class Search(val cardBIN: String) : MainUiEvent
    object ErrorHandled: MainUiEvent
}