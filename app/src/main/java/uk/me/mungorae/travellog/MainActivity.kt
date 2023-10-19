package uk.me.mungorae.travellog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import uk.me.mungorae.travellog.ui.theme.TravelLogTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val getPhotos = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia()) {

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TravelLogTheme {
                MainNavGraph()
            }
        }


    }
}