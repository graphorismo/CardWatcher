package ru.graphorismo.cardwatcher.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.graphorismo.cardwatcher.data.local.ILocalHistoryRepository
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val localHistoryRepository: ILocalHistoryRepository) : ViewModel() {

    var uiState = MutableStateFlow(HistoryUiState(listOf()))

    fun onEvent(event: HistoryUiEvent){
        when (event){
            is HistoryUiEvent.LoadActivity -> {
                viewModelScope.launch {
                    uiState.value = HistoryUiState(lines = localHistoryRepository.getHistory())
                }
            }
            is HistoryUiEvent.ClickHistoryLine -> {
                uiState.value = HistoryUiState(listOf(), lineCallback = event.line)
            }
            is HistoryUiEvent.ClickBackButton -> {
                uiState.value = HistoryUiState(listOf(), lineCallback = "")
            }
            is HistoryUiEvent.ClickClearButton -> {
                viewModelScope.launch {
                    localHistoryRepository.clearHistory()
                }
                uiState.value = HistoryUiState(listOf())
            }
        }
    }
}