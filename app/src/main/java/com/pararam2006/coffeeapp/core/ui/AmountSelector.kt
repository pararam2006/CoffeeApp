package com.pararam2006.coffeeapp.core.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pararam2006.coffeeapp.R
import com.pararam2006.coffeeapp.ui.theme.CoffeeAppTheme
import com.pararam2006.coffeeapp.ui.theme.LocationCardTextPrimary

@Composable
fun AmountSelector(
    count: Int,
    onPlusPressed: () -> Unit,
    onMinusPressed: () -> Unit,
    buttonsColor: Color,
    counterTextStyle: TextStyle,
    modifier: Modifier = Modifier,
) {
    val iconSize = 30.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier,
    ) {
        IconButton(onClick = onMinusPressed) {
            Icon(
                painter = painterResource(R.drawable.akar_icons_minus),
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = buttonsColor
            )
        }

        Text(
            text = "$count",
            style = counterTextStyle
        )

        IconButton(onClick = onPlusPressed) {
            Icon(
                painter = painterResource(R.drawable.akar_icons_plus),
                contentDescription = null,
                modifier = Modifier.size(iconSize),
                tint = buttonsColor
            )
        }
    }
}

@Preview
@Composable
private fun AmountSelectorPreview() {
    CoffeeAppTheme {
        AmountSelector(
            count = 1,
            onPlusPressed = {},
            onMinusPressed = {},
            buttonsColor = Color(0xFFF6E5D1),
            counterTextStyle = TextStyle(
                color = LocationCardTextPrimary,
                fontSize = 18.sp
            ),
        )
    }
}