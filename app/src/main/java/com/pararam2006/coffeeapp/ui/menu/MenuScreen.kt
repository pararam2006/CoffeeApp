package com.pararam2006.coffeeapp.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.pararam2006.coffeeapp.R
import com.pararam2006.coffeeapp.core.ui.AmountSelector
import com.pararam2006.coffeeapp.core.ui.CoffeeButton
import com.pararam2006.coffeeapp.domain.dto.MenuItemDto
import com.pararam2006.coffeeapp.ui.theme.AmountSelectorButtonsPrimary
import com.pararam2006.coffeeapp.ui.theme.AmountSelectorCounterPrimary
import com.pararam2006.coffeeapp.ui.theme.CoffeeAppTheme
import com.pararam2006.coffeeapp.ui.theme.LocationCardTextPrimary
import com.pararam2006.coffeeapp.ui.theme.LocationCardTextSecondary
import com.pararam2006.coffeeapp.ui.theme.MenuItemBackgroundPrimaty
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun MenuScreen(
    menu: List<MenuItemDto>,
    onNavigateToOrder: (menuItemsJson: String) -> Unit,
    onLoadMenu: () -> Unit,
    modifier: Modifier = Modifier,
    onPlusPressed: (id: Int) -> Unit,
    onMinusPressed: (id: Int) -> Unit
) {
    LaunchedEffect(Unit) {
        onLoadMenu()
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 15.dp),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(15.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
        ) {
            items(menu) { menuItem ->
                MenuItem(
                    coffeeName = menuItem.name,
                    price = menuItem.price,
                    count = menuItem.count,
                    imageUrl = menuItem.imageUrl,
                    onPlusPressed = { onPlusPressed(menuItem.id) },
                    onMinusPressed = { onMinusPressed(menuItem.id) },
                )
            }
        }
        CoffeeButton(
            text = "Перейти к оплате",
            onClick = {
                val selectedMenuItems = menu.filter { it.count > 0 }
                val menuItemsJson = Json.encodeToString(selectedMenuItems)
                onNavigateToOrder(menuItemsJson)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.9f)
        )
    }
}

@Composable
fun MenuItem(
    coffeeName: String,
    price: Int,
    count: Int,
    imageUrl: String,
    onPlusPressed: () -> Unit,
    onMinusPressed: () -> Unit,
) {
    Card(
        modifier = Modifier
            .width(165.dp)
            .height(205.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.9f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = if (imageUrl.isNotEmpty()) {
                    rememberAsyncImagePainter(imageUrl)
                } else {
                    painterResource(R.drawable.ic_launcher_foreground)
                },
                contentDescription = null
            )
        }

        Box(
            modifier = Modifier
                .weight(0.4f)
                .background(MenuItemBackgroundPrimaty)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp),
            ) {
                Text(
                    text = coffeeName, style = TextStyle(
                        color = LocationCardTextSecondary,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.padding(top = 5.dp)
                )


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "$price руб",
                        style = TextStyle(
                            color = LocationCardTextPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    )

                    AmountSelector(
                        count = count,
                        onPlusPressed = onPlusPressed,
                        onMinusPressed = onMinusPressed,
                        buttonsColor = AmountSelectorButtonsPrimary,
                        counterTextStyle = TextStyle(
                            color = AmountSelectorCounterPrimary,
                            fontSize = 18.sp
                        ),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MenuItemPreview() {
    CoffeeAppTheme {
        MenuItem(
            coffeeName = "Горячий шоколад",
            price = 200,
            count = 15,
            onPlusPressed = {},
            onMinusPressed = {},
            imageUrl = "",
        )
    }
}

@Preview
@Composable
private fun MenuPreview() {
    CoffeeAppTheme {
        MenuScreen(
            menu = remember {
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
            onNavigateToOrder = {},
            onLoadMenu = {},
            onPlusPressed = {},
            onMinusPressed = {}
        )
    }
}
