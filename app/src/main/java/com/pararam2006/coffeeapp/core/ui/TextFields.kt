package com.pararam2006.coffeeapp.core.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pararam2006.coffeeapp.ui.theme.CoffeeAppTheme
import com.pararam2006.coffeeapp.ui.theme.ShapeColorPrimary
import com.pararam2006.coffeeapp.ui.theme.TextPrimary

@Composable
fun CoffeeTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    placeholderText: String = "",
    labelText: String = ""
) {
    Column {
        Text(
            text = labelText,
            style = TextStyle(color = TextPrimary)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = ShapeColorPrimary,
                    shape = RoundedCornerShape(24.dp)
                )
                .clip(RoundedCornerShape(24.dp))
        ) {
            TextField(
                value = value,
                onValueChange = onValueChange,
                modifier = modifier,
                singleLine = true,
                placeholder = {
                    Text(
                        text = placeholderText,
                        style = TextStyle(color = TextPrimary)
                    )
                },
                shape = RoundedCornerShape(24.dp),
                textStyle = TextStyle(color = TextPrimary)
            )
        }
    }
}

@Preview
@Composable
private fun InputTextFieldPasswordPreview() {
    CoffeeAppTheme {
        CoffeeTextField(
            value = "",
            onValueChange = {},
            labelText = "Пароль",
            placeholderText = "******"
        )
    }
}

@Preview
@Composable
private fun InputTextFieldEmailPreview() {
    CoffeeAppTheme {
        CoffeeTextField(
            value = "",
            onValueChange = {},
            labelText = "e-mail",
            placeholderText = "example@example.ru"
        )
    }
}

@Preview
@Composable
private fun InputTextFieldEmailPreviewWithText() {
    CoffeeAppTheme {
        CoffeeTextField(
            value = "benderkott",
            onValueChange = {},
            labelText = "e-mail",
            placeholderText = "example@example.ru"
        )
    }
}