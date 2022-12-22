package ru.graphorismo.cardwatcher.ui.main

sealed interface MainUiEvent{
    class Search(val cardBIN: String) : MainUiEvent
    object ErrorHandled: MainUiEvent
}