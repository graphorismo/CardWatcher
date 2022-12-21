package ru.graphorismo.cardwatcher.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.graphorismo.cardwatcher.domain.IRemoteRepository
import ru.graphorismo.cardwatcher.domain.card.CardData
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val remoteRepository: IRemoteRepository): ViewModel() {

    val mainUiState = MutableStateFlow(CardData())

    fun onEvent(mainUiEvent: MainUiEvent){
        when(mainUiEvent){
            is MainUiEvent.Search ->{
                viewModelScope.launch(Dispatchers.Default) {
                    mainUiState.value = remoteRepository.getCardDataByBIN(mainUiEvent.cardBIN)
                }
            }
        }
    }

}