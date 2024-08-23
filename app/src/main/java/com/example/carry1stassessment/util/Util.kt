package com.example.carry1stassessment.util

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat


@Composable
fun FormattedCurrencyText(input: Int, currency: String) {
    Text(
        modifier = Modifier
            .padding(top = 10.dp),
        text = getFormattedAmt(input, currency),
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 20.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF021218),
        )
    )
}

fun getFormattedAmt(input: Int, currency: String): String {
    val pattern = "#,##0.00"
    val decimalFormat = DecimalFormat(pattern)
    return currency + decimalFormat.format(input)
}
