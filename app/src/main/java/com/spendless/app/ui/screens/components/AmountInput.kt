package com.spendless.app.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun AmountInput(
    amount: String,
    onAmountChange: (String) -> Unit,
    showRedBrackets: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$",
            color = Color.Red,
            style = MaterialTheme.typography.headlineMedium
        )

        BasicTextField(
            value = amount,
            onValueChange = onAmountChange,
            textStyle = TextStyle(
                color = if (amount == "00.00") Color.Gray else Color.Black,
                textAlign = TextAlign.Center,
                fontSize = 24.sp
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        if (showRedBrackets) {
            Text(")", color = Color.Red)
        } else {
            Text("-", color = Color.Red)
        }
    }
}