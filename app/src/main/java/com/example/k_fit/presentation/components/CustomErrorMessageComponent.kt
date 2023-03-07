package com.example.k_fit.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.k_fit.R

@Composable
fun CustomErrorMessageComponent(
    errorMessage: Int
){
    Text(
        text = stringResource(errorMessage),
        color = Color.Red,
        modifier = Modifier.padding(top = 8.dp)
    )
}


@Preview("Preview of the ErrorMessage component", showBackground = true)
@Composable
fun PreviewCustomErrorMessageComponent(){
    CustomErrorMessageComponent(R.string.empty_form)
}