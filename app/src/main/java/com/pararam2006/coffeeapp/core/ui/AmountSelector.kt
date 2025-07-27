package com.pararam2006.coffeeapp.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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
import com.pararam2006.coffeeapp.ui.theme.AmountSelectorButtonsPrimary
import com.pararam2006.coffeeapp.ui.theme.AmountSelectorCounterPrimary
import com.pararam2006.coffeeapp.ui.theme.CoffeeAppTheme
import com.pararam2006.coffeeapp.ui.theme.LocationCardTextPrimary

@Composable
fun AmountSelector(
    count: Int,
    onPlusPressed: () -> Unit,
    onMinusPressed: () -> Unit,
    buttonsColor: Color = AmountSelectorButtonsPrimary,
    counterTextStyle: TextStyle = TextStyle(
        color = AmountSelectorCounterPrimary,
        fontSize = 18.sp
    ),
    modifier: Modifier = Modifier,
) {
    val iconSize = 25.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(R.drawable.akar_icons_minus),
            contentDescription = null,
            modifier = Modifier
                .size(iconSize)
                .padding(all = 0.dp)
                .clickable { onMinusPressed() },
            tint = buttonsColor
        )

        Text(
            text = "$count",
            style = counterTextStyle
        )

        Icon(
            painter = painterResource(R.drawable.akar_icons_plus),
            contentDescription = null,
            modifier = Modifier
                .size(iconSize)
                .clickable { onPlusPressed() },
            tint = buttonsColor
        )
    }
}

@Preview
@Composable
private fun AmountSelectorPreview() {
    CoffeeAppTheme {
        AmountSelector(
            count = 15,
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