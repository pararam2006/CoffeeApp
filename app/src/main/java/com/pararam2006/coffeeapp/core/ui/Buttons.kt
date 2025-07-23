package com.pararam2006.coffeeapp.core.ui

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.pararam2006.coffeeapp.ui.theme.EnabledButtonPrimary
import com.pararam2006.coffeeapp.ui.theme.EnabledButtonTextPrimary
import com.pararam2006.coffeeapp.ui.theme.CoffeeAppTheme

@Composable
fun CoffeeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        colors = ButtonColors(
            containerColor = EnabledButtonPrimary,
            contentColor = EnabledButtonTextPrimary,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        )
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = EnabledButtonTextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )
    }
}

@Preview
@Composable
private fun CoffeeButtonPreview() {
    CoffeeAppTheme {
        CoffeeButton(
            text = "Зарегистрироваться",
            onClick = {},
            enabled = true
        )
    }
}