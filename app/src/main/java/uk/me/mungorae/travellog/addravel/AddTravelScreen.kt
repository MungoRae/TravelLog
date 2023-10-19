package uk.me.mungorae.travellog.addravel

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import uk.me.mungorae.travellog.R
import uk.me.mungorae.travellog.util.DateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTravelScreen(
    onTravelsUpdated: () -> Unit,
    viewModel: AddTravelViewModel = hiltViewModel(),
) {
    val (images, setImages) = remember { mutableStateOf<List<Uri>>(listOf()) }
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
    ) {
        setImages(images.plus(it))
    }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = stringResource(id = R.string.add_travel_title)) })
        }
    ) {
        AddTravelContent(
            modifier = Modifier.padding(it),
            uiState,
            images,
            viewModel::onNameChange,
            viewModel::onDescriptionChange,
            viewModel::onDateSelected,
            { photoPickerLauncher.launch(PickVisualMediaRequest()) },
            viewModel::onSubmit,
        )

        LaunchedEffect(key1 = uiState.isTravelSaved) {
            if (uiState.isTravelSaved) {
                onTravelsUpdated()
            }
        }
    }
}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTravelContent(
    modifier: Modifier = Modifier,
    uiState: AddTravelUiState = AddTravelUiState(date = PreviewDateTime()),
    images: List<Uri> = emptyList(),
    onNameChanged: (String) -> Unit = {},
    onDescriptionChanged: (String) -> Unit = {},
    onDatePickerDateSelected: (Int, Int, Int) -> Unit = { _, _, _ -> },
    onAddImagePressed: () -> Unit = {},
    onSubmit: () -> Unit = {},
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 8.dp)
    ) {
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
        DateTextField(date = uiState.date, onDateChanged = onDatePickerDateSelected)
        LazyRow(
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                ImagePreviewContainer {
                    AddImageButton { onAddImagePressed() }
                }
            }
            items(images.size) {
                val uri = images[it]
                ImagePreviewContainer {
                    ImagePreview(uri = uri, count = it)
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onSubmit, modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text(text = stringResource(id = R.string.add_travel_button_confirm))
        }
    }
}

@Composable
fun ImagePreviewContainer(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .size(width = 100.dp, height = 130.dp)
            .clip(CircleShape.copy(all = CornerSize(4.dp)))
    ) {
        content()
    }
}

@Composable
fun ImagePreview(uri: Uri, count: Int) {
    AsyncImage(
        model = uri,
        contentDescription = stringResource(id = R.string.add_travel_image_description, count),
        contentScale = ContentScale.Crop,
    )
}

@Composable
fun AddImageButton(onAddImagePressed: () -> Unit = {}) {
    IconButton(
        modifier = Modifier.fillMaxSize(),
        colors = IconButtonDefaults.filledIconButtonColors(),
        onClick = { onAddImagePressed() },
    ) {
        Icon(
            painter = painterResource(id = R.drawable.add_photo),
            contentDescription = stringResource(
                id = R.string.add_travel_button_add_photo
            )
        )
    }
}

class PreviewDateTime : DateTime {
    override fun toString(): String = "Preview"

    override fun toLongDateString() = "Preview"

    override fun year() = 2011

    override fun month() = 1

    override fun dayOfMonth() = 1
}