package uk.me.mungorae.travellog.addravel

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import uk.me.mungorae.travellog.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTravelScreen(
    onTravelsUpdated: () -> Unit,
    viewModel: AddTravelViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.add_travel_title)) })
        }
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        AddTravelContent(
            uiState,
            viewModel::onNameChange,
            viewModel::onDescriptionChange,
            viewModel::onDateSelected,
            viewModel::onSubmit,
            modifier = Modifier.padding(it)
        )

        LaunchedEffect(key1 = uiState.isTravelSaved) {
            if (uiState.isTravelSaved) {
                onTravelsUpdated()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTravelContent(
    uiState: AddTravelUiState,
    onNameChanged: (String) -> Unit,
    onDescriptionChanged: (String) -> Unit,
    onDatePickerDateSelected: (Int, Int, Int) -> Unit,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    val datePicker = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            onDatePickerDateSelected(selectedYear, selectedMonth + 1, selectedDayOfMonth)
        }, uiState.date.year(), uiState.date.month() - 1, uiState.date.dayOfMonth()
    )
    Column(modifier = modifier.padding(horizontal = 8.dp)) {
        TextField(
            label = { Text(text = stringResource(id = R.string.add_travel_label_name)) },
            value = uiState.name,
            onValueChange = onNameChanged,
            modifier = Modifier.padding(top = 8.dp),
        )
        TextField(
            label = { Text(text = stringResource(id = R.string.add_travel_label_description)) },
            value = uiState.description,
            onValueChange = onDescriptionChanged,
            modifier = Modifier.padding(top = 8.dp),
        )
        TextField(
            value = uiState.date.toLongDateString(),
            onValueChange = {},
            modifier = Modifier
                .padding(top = 8.dp)
                .clickable {
                    Log.d("TIMBER", "Showing!")
                    datePicker.show()
                },
            readOnly = true,
            enabled = false,
            colors = TextFieldDefaults.textFieldColors(
                disabledTextColor = LocalContentColor.current,
                disabledLabelColor = LocalContentColor.current,
            ),
            label = {
                Text(text = stringResource(id = R.string.add_travel_label_date))
            },
        )
        Button(onClick = onSubmit, modifier = Modifier.padding(top = 8.dp)) {
            Text(text = stringResource(id = R.string.add_travel_button_confirm))
        }
    }
}
