package uk.me.mungorae.travellog.addravel

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import uk.me.mungorae.travellog.R
import uk.me.mungorae.travellog.util.DateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTextField(date: DateTime, onDateChanged: (Int, Int, Int) -> Unit) {
    val datePicker = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            onDateChanged(selectedYear, selectedMonth + 1, selectedDayOfMonth)
        }, date.year(), date.month() - 1, date.dayOfMonth()
    )
    TextField(
        value = date.toLongDateString(),
        onValueChange = {},
        modifier = Modifier
            .padding(top = 8.dp)
            .clickable {
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
}