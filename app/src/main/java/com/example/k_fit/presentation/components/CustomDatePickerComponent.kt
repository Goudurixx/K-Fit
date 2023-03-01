package com.example.k_fit.presentation.components

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.k_fit.R
import com.example.k_fit.ui.theme.*
import java.util.*


@Composable
fun CustomDatePickerComponent(
    title: String,
    hint: String,
    inputText: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onClick:  (Context) -> Unit
) {
//    val year: Int
//    val month: Int
//    val day: Int
//    val calendar = Calendar.getInstance()
//    year = calendar.get(Calendar.YEAR)
//    month = calendar.get(Calendar.MONTH)
//    day = calendar.get(Calendar.DAY_OF_MONTH)
//    calendar.time = Date()

//    val mDate = remember { mutableStateOf("") }

//    val mDatePickerDialog = DatePickerDialog(
//        LocalContext.current, R.style.DatePickerTheme,
//        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
//            inputText =
//                "$selectedDayOfMonth/${String.format("%02d", selectedMonth + 1)}/$selectedYear"
//        }, year, month, day
//    )

    Column {
//        OutlinedTextField(
//            enabled = false,
//            label = { Text(text = title) },
//            value = inputText,
//            placeholder = { Text(hint) },
//            onValueChange = onValueChange,
//            textStyle = TextStyle(
//                fontSize = 20.sp,
//                color = HintTextColor
//            ),
//            modifier = modifier
//                .fillMaxWidth()
//                .clickable(
//                    interactionSource = remember { MutableInteractionSource() },
//                    indication = null,
//                    onClick = onClick(LocalContext.current)
//                ),
//            readOnly = false,
//            singleLine = true,
//            trailingIcon = {
//                IconButton(
//                    onClick = {},
//                ) {
//                    Icon(imageVector = Icons.Filled.Today, "Clean field")
//                }
//            }
//        )
    }
}

//@Preview("Preview CustomButtomComponent", showBackground = true, showSystemUi = true)
//@Composable
//fun PreviewCustomDatePickerComponent() {
//    val onValueChange: (String) -> Unit = { s: String -> println(s) }
//    CustomDatePickerComponent(
//        "Title of the input",
//        hint = "Hint for the input",
//        onValueChange = onValueChange,
//    )
//}


