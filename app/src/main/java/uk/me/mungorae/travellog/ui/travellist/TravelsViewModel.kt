package uk.me.mungorae.travellog.ui.travellist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uk.me.mungorae.travellog.data.Travel
import uk.me.mungorae.travellog.data.TravelsRepository
import javax.inject.Inject

data class TravelsUiState(val travels: List<Travel> = emptyList())

@HiltViewModel
class TravelsViewModel @Inject constructor(
    private val travelsRepository: TravelsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(TravelsUiState())
    val uiState: StateFlow<TravelsUiState> = _uiState

    init {
        viewModelScope.launch {
            travelsRepository.travelsStream().collect { latest ->
                _uiState.update { it.copy(travels = latest) }
            }
        }
    }
}
