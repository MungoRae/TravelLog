package uk.me.mungorae.travellog.addravel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uk.me.mungorae.travellog.data.Travel
import uk.me.mungorae.travellog.data.TravelsRepository
import javax.inject.Inject

data class AddTravelUiState(
    val name: String = "",
    val description: String = "",
    val isTravelSaved: Boolean = false
)

@HiltViewModel
class AddTravelViewModel @Inject constructor(private val travelsRepository: TravelsRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(AddTravelUiState())
    val uiState: StateFlow<AddTravelUiState> = _uiState.asStateFlow()

    fun onNameChange(newName: String) {
        _uiState.update { it.copy(name = newName) }
    }

    fun onDescriptionChange(newDescription: String) {
        _uiState.update { it.copy(description = newDescription) }
    }

    fun onSubmit() {
        travelsRepository.createTravel(Travel(name = uiState.value.name, uiState.value.description))
        _uiState.update { it.copy(isTravelSaved = true) }
    }
}