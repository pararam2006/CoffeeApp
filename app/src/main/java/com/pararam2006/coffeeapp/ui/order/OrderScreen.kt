package com.pararam2006.coffeeapp.ui.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pararam2006.coffeeapp.core.ui.AmountSelector
import com.pararam2006.coffeeapp.core.ui.CoffeeButton
import com.pararam2006.coffeeapp.domain.dto.MenuItemDto
import com.pararam2006.coffeeapp.ui.theme.CoffeeAppTheme
import com.pararam2006.coffeeapp.ui.theme.LocationCardBackgroundPrimary
import com.pararam2006.coffeeapp.ui.theme.LocationCardTextPrimary
import com.pararam2006.coffeeapp.ui.theme.LocationCardTextSecondary
import com.pararam2006.coffeeapp.ui.theme.TextPrimary

@Composable
fun OrderScreen(
    modifier: Modifier = Modifier,
    orderedCoffee: List<MenuItemDto>,
    onPlusPressed: (id: Int) -> Unit,
    onMinusPressed: (id: Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 15.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(orderedCoffee) { orderedItem ->
                OrderedItem(
                    name = orderedItem.name,
                    price = orderedItem.price,
                    onPlusPressed = { onPlusPressed(orderedItem.id) },
                    onMinusPressed = { onMinusPressed(orderedItem.id) },
                    count = orderedItem.count
                )
            }
        }

        Text(
            text = "Время ожидания заказа\n" +
                    "15 минут!\n" +
                    "Спасибо, что выбрали нас!",
            modifier = Modifier.padding(vertical = 16.dp),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = LocationCardTextPrimary,
            ),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        CoffeeButton(
            text = "Оплатить",
            onClick = {},
            modifier = Modifier
                .fillMaxWidth(0.9f),
        )
    }
}

@Composable
fun OrderedItem(
    name: String,
    price: Int,
    onPlusPressed: () -> Unit,
    onMinusPressed: () -> Unit,
    count: Int
) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = LocationCardBackgroundPrimary
        ),
        modifier = Modifier
            .width(349.dp)
            .height(71.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 10.dp)
            ) {
                Spacer(
                    modifier = Modifier.height(4.dp)
                )
                Text(
                    text = name, style = TextStyle(
                        color = LocationCardTextPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )
                Text(
                    text = "$price руб",
                    style = TextStyle(
                        color = LocationCardTextSecondary,
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            AmountSelector(
                count = count,
                onPlusPressed = onPlusPressed,
                onMinusPressed = onMinusPressed,
                buttonsColor = TextPrimary
            )
        }
    }
}


@Preview
@Composable
private fun OrderScreenPreview() {
    CoffeeAppTheme {
        OrderScreen(
            orderedCoffee = remember {
                mutableStateListOf(
                    MenuItemDto(
                        id = 0,
                        name = "Эспрессо",
                        imageUrl = "",
                        price = 200,
                        count = 0,
                    ),
                    MenuItemDto(
                        id = 0,
                        name = "Эспрессо",
                        imageUrl = "",
                        price = 200,
                        count = 0,
                    ),
                    MenuItemDto(
                        id = 0,
                        name = "Эспрессо",
                        imageUrl = "",
                        price = 200,
                        count = 0,
                    ),
                    MenuItemDto(
                        id = 0,
                        name = "Эспрессо",
                        imageUrl = "",
                        price = 200,
                        count = 0,
                    ),
                    MenuItemDto(
                        id = 0,
                        name = "Эспрессо",
                        imageUrl = "",
                        price = 200,
                        count = 0,
                    ),
                    MenuItemDto(
                        id = 0,
                        name = "Эспрессо",
                        imageUrl = "",
                        price = 200,
                        count = 0,
                    ),
                )
            },
            onPlusPressed = {},
            onMinusPressed = {}
        )
    }
}