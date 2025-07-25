package com.pararam2006.coffeeapp.ui.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
    orderList: List<MenuItemDto>, modifier: Modifier = Modifier
) {
    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier.padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(orderList) { orderItem ->
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
                                text = orderItem.name, style = TextStyle(
                                    color = LocationCardTextPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            )
                            Text(
                                text = "${orderItem.price} руб", style = TextStyle(
                                    color = LocationCardTextSecondary, fontSize = 14.sp
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                        }

                        AmountSelector(
                            count = 0,
                            onPlusPressed = {},
                            onMinusPressed = {},
                            buttonsColor = TextPrimary,
                            counterTextStyle = TextStyle(
                                color = TextPrimary, fontSize = 18.sp
                            ),
                        )
                    }
                }

            }
        }
        Box(
            modifier = Modifier.padding(start = 30.dp, end = 30.dp)
        ) {
            Text(
                text = "Время ожидания заказа \n15 минут!\n Спасибо, что выбрали нас!",
                style = TextStyle(
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                textAlign = TextAlign.Center
            )
        }
        CoffeeButton(
            text = "Оплатить",
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.9f)
        )
    }
}


@Preview
@Composable
private fun OrderScreenPreview() {
    CoffeeAppTheme {
        OrderScreen(
            orderList = listOf(
                MenuItemDto(
                    id = 0, name = "Капучино", imageUrl = "", price = 200
                ),
                MenuItemDto(
                    id = 0, name = "Эспрессо", imageUrl = "", price = 200
                ),
                MenuItemDto(
                    id = 0, name = "Эспрессо", imageUrl = "", price = 200
                ),
            )
        )
    }
}