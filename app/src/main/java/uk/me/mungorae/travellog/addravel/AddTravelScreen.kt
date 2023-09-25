package uk.me.mungorae.travellog.addravel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
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
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = "Add travel here")
        TextField(
            label = { Text(text = stringResource(id = R.string.add_travel_label_name)) },
            value = uiState.name,
            onValueChange = onNameChanged
        )
        TextField(
            label = { Text(text = stringResource(id = R.string.add_travel_label_description)) },
            value = uiState.description,
            onValueChange = onDescriptionChanged
        )
        Button(onClick = onSubmit) {
            Text(text = stringResource(id = R.string.add_travel_button_confirm))
        }
    }
}
