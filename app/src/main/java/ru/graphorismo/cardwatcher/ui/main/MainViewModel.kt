package ru.graphorismo.cardwatcher.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.graphorismo.cardwatcher.data.remote.exceptions.BlankBinException
import ru.graphorismo.cardwatcher.data.remote.exceptions.CardNotFoundException
import ru.graphorismo.cardwatcher.data.remote.exceptions.RequestTimeoutException
import ru.graphorismo.cardwatcher.data.remote.IRemoteRepository
import ru.graphorismo.cardwatcher.domain.card.MainUiState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val remoteRepository: IRemoteRepository): ViewModel() {

    val mainUiState = MutableStateFlow(MainUiState())

    fun onEvent(mainUiEvent: MainUiEvent){
        when(mainUiEvent){
            is MainUiEvent.Search ->{
                viewModelScope.launch(Dispatchers.Default) {
                    try{
                        val cardData = remoteRepository.getCardDataByBIN(mainUiEvent.cardBIN)
                        mainUiState.value = MainUiState(cardData=cardData)
                    }catch (ex: BlankBinException){
                        mainUiState.value = MainUiState(exception = ex)
                    }catch (ex: CardNotFoundException){
                        mainUiState.value = MainUiState(exception = ex)
                    }catch (ex: RequestTimeoutException){
                        mainUiState.value = MainUiState(exception = ex)
                    }catch (ex: Exception){
                        mainUiState.value = MainUiState(exception = ex)
                    }
                }
            }
            is MainUiEvent.ErrorHandled -> {
                mainUiState.value = MainUiState()
            }
        }
    }

}