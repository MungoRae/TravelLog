package uk.me.mungorae.travellog.ui.travellist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import uk.me.mungorae.travellog.R
import uk.me.mungorae.travellog.data.Travel
import uk.me.mungorae.travellog.ui.reuseable.PreviewDateTime

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

class TravelListPreview : PreviewParameterProvider<List<Travel>> {
    override val values: Sequence<List<Travel>> = sequenceOf(
        listOf(
            Travel(
                name = "My Excellent Holiday",
                description = "This was when I went to blah blah blah and found lots of nice stuff that was really great.",
                imageUrls = listOf("https://localhost"),
                date = PreviewDateTime(),
            ),
        ),
    )
}

@Preview(showSystemUi = true)
@Composable
fun TravelsContent(
    @PreviewParameter(provider = TravelListPreview::class) travels: List<Travel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(travels) { travel ->
            TravelItem(travel = travel)
        }
    }
}

@Composable
fun TravelItem(travel: Travel, modifier: Modifier = Modifier) {
    ElevatedCard(
        modifier = modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
    ) {
        Column(modifier = Modifier) {
            if (travel.imageUrls.isNotEmpty()) {
                AsyncImage(
                    model = travel.imageUrls[0],
                    placeholder = painterResource(id = R.drawable.placeholder_travel_item),
                    contentDescription = "Cover image of the travel",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth().height(200.dp),
                    colorFilter = ColorFilter.tint(Color.Blue, blendMode = BlendMode.Darken),
                )
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = travel.date.toLongDateString(),
                    style = MaterialTheme.typography.labelSmall,
                )
                Text(
                    text = travel.name,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(top = 8.dp),
                )
                Text(
                    text = travel.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }
        }
    }
}
