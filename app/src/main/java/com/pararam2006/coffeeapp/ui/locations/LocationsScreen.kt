package com.pararam2006.coffeeapp.ui.locations

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pararam2006.coffeeapp.core.ui.CoffeeButton
import com.pararam2006.coffeeapp.domain.dto.LocationsDto
import com.pararam2006.coffeeapp.domain.dto.PointDto
import com.pararam2006.coffeeapp.ui.theme.CoffeeAppTheme
import com.pararam2006.coffeeapp.ui.theme.LocationCardBackgroundPrimary
import com.pararam2006.coffeeapp.ui.theme.LocationCardTextPrimary
import com.pararam2006.coffeeapp.ui.theme.LocationCardTextSecondary

@Composable
fun LocationsScreen(
    locations: List<LocationsDto>,
    onLoadLocations: () -> Unit,
    modifier: Modifier = Modifier,
    onNavigateToMap: () -> Unit,
) {
    LaunchedEffect(Unit) {
        onLoadLocations()
    }
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            items(locations) { location ->
                Log.d("LocationsScreen", location.name)
                Location(location.name)
            }
        }

        CoffeeButton(
            text = "На карте",
            onClick = onNavigateToMap,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.9f),
        )
    }
}

@Composable
fun Location(name: String) {
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
                text = "1км от вас", //TODO заменить на реальные данные от геолокации
                style = TextStyle(
                    color = LocationCardTextSecondary,
                    fontSize = 14.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview
@Composable
private fun LocationsScreenPreview() {
    CoffeeAppTheme {
        LocationsScreen(
            locations = listOf(
                LocationsDto(
                    id = 0, name = "BEDOEV COFFEE", point = PointDto(
                        latitude = 0.0, longitude = 0.0
                    )
                ),
                LocationsDto(
                    id = 0, name = "Coffee Like", point = PointDto(
                        latitude = 0.0, longitude = 0.0
                    )
                ),
                LocationsDto(
                    id = 0, name = "EM&DI Coffee and Snacks", point = PointDto(
                        latitude = 0.0, longitude = 0.0
                    )
                ),
                LocationsDto(
                    id = 0, name = "Коффе есть", point = PointDto(
                        latitude = 0.0, longitude = 0.0
                    )
                ),
                LocationsDto(
                    id = 0, name = "Набоков", point = PointDto(
                        latitude = 0.0, longitude = 0.0
                    )
                ),

                ),
            onLoadLocations = {},
            onNavigateToMap = {}
        )
    }
}