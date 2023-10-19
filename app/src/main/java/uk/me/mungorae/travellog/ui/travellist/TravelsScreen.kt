package uk.me.mungorae.travellog.ui.travellist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import uk.me.mungorae.travellog.R
import uk.me.mungorae.travellog.data.Travel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelsScreen(
    onAddTravel: () -> Unit,
    viewModel: TravelsViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.travels_screen_title))
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTravel) {
                Icon(Icons.Filled.Add, stringResource(id = R.string.travels_screen_button_add))
            }
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
        TravelsContent(travels = uiState.travels, modifier = Modifier.padding(it))
    }
}

@Composable
fun TravelsContent(
    travels: List<Travel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(travels) { travel ->
            TravelItem(travel = travel)
        }
    }
}

@Composable
fun TravelItem(travel: Travel) {
    Column {
        Text(text = travel.name)
        Text(text = travel.description)
    }
}
