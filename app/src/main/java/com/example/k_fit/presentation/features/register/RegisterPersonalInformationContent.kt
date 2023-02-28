package com.example.k_fit.presentation.features.register

import android.app.DatePickerDialog
import android.graphics.drawable.ColorDrawable
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.k_fit.presentation.components.CustomInputTextComponent
import java.util.*
import com.example.k_fit.R
import com.example.k_fit.presentation.components.CustomDatePickerComponent

@Composable
fun RegisterPersonalInformation(
    viewModel: RegisterViewModel
) {

    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val mCalendar = Calendar.getInstance()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()
    val mDate = remember { mutableStateOf("") }

    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current, R.style.DatePickerTheme,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${String.format("%02d", mMonth + 1)}/$mYear"
        }, mYear, mMonth, mDay
    )

    val registerState by viewModel.registerState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(
                all = 16.dp
            )
    ) {
        CustomInputTextComponent(
            title = "Nickname",
            hint = "Nickname",
            inputText = registerState.nickName,
            onValueChange = { viewModel.updateNickName(it) },
        )
        CustomInputTextComponent(
            title = "Firstname",
            hint = "Firstname",
            inputText = registerState.firstName,
            onValueChange = { viewModel.updateFirstName(it) },
        )
        CustomInputTextComponent(
            title = "Lastname",
            hint = "Lastname",
            inputText = registerState.lastName,
            onValueChange = { viewModel.updateLastName(it) },
        )
        CustomDatePickerComponent(
            title = "Birthdate",
            hint = "Birthdate",
            inputText = mDate.value,
            onValueChange = {},
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    mDatePickerDialog.show()
                }
            ),
        )
    }
}
