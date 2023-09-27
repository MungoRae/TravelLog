package uk.me.mungorae.travellog.addravel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import uk.me.mungorae.travellog.data.Travel
import uk.me.mungorae.travellog.data.TravelsRepository
import uk.me.mungorae.travellog.util.DateTime
import uk.me.mungorae.travellog.util.DateTimeProvider
import javax.inject.Inject

data class AddTravelUiState(
    val name: String = "",
    val description: String = "",
    val date: DateTime,
    val isTravelSaved: Boolean = false
)

@HiltViewModel
class AddTravelViewModel @Inject constructor(
    private val travelsRepository: TravelsRepository,
    private val dateTimeProvider: DateTimeProvider,
) : ViewModel() {
    private val _uiState = MutableStateFlow(
        AddTravelUiState(date = dateTimeProvider.today())
    )
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

    fun onDateSelected(year: Int, month: Int, day: Int) {
        _uiState.update {
            it.copy(
                date = dateTimeProvider.createDateTimeFromYearMonthDay(
                    year,
                    month,
                    day
                )
            )
        }

    }
}