package com.pararam2006.coffeeapp.ui.map

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.graphics.createBitmap
import com.pararam2006.coffeeapp.R
import com.yandex.mapkit.Animation
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.image.ImageProvider
import org.koin.androidx.compose.koinViewModel

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    startPoint: Point,
    startZoom: Float = 11f,
    onMapReady: ((MapView) -> Unit)? = null,
    onMarkerClick: (Int) -> Unit
) {
    val viewModel: MapScreenViewModel = koinViewModel()
    var mapView by remember { mutableStateOf<MapView?>(null) }
    val context = LocalContext.current
    val markersFromViewModel by viewModel.markers.collectAsState()

    AndroidView(
        modifier = modifier,
        factory = { context ->
            MapView(context).apply {
                mapView = this

                map.move(
                    CameraPosition(
                        startPoint, startZoom, 0f, 0f
                    ),
                    Animation(Animation.Type.SMOOTH, 0f),
                    null
                )

                onMapReady?.invoke(this)
            }
        },
    )

    LaunchedEffect(mapView, markersFromViewModel) {
        mapView?.let { map ->
            map.map.mapObjects.clear()

            // Центрирование карты на первом маркере, если список не пуст
            if (markersFromViewModel.isNotEmpty()) {
                val firstMarkerPoint = markersFromViewModel.first().point
                map.map.move(
                    CameraPosition(firstMarkerPoint, startZoom, 0f, 0f),
                    Animation(Animation.Type.SMOOTH, 1f), // Плавное перемещение
                    null
                )
            }

            val iconBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.coffee_icon)
            markersFromViewModel.forEach { markerDto ->
                val markerBitmap = createMarkerBitmap(context, iconBitmap, markerDto.name)
                val imageProvider = ImageProvider.fromBitmap(markerBitmap)
                val placemark = map.map.mapObjects.addPlacemark(markerDto.point, imageProvider)
                placemark.userData = markerDto.id

                placemark.addTapListener { mapObject, _ ->
                    val id = mapObject.userData as? Int
                    if (id != null) {
                        onMarkerClick(id)
                    }
                    true
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mapView?.onStop()
        }
    }
}

//Костыль, чтобы расположить текст под иконкой
fun createMarkerBitmap(context: android.content.Context, icon: Bitmap, text: String): Bitmap {
    val density = context.resources.displayMetrics.density
    val textSizeDp = 14f
    val textSizePx = textSizeDp * density

    val textColor = Color(0xFF846340) // Новый цвет текста
    val horizontalPaddingDp = 8f
    val verticalPaddingDp = 4f

    val horizontalPaddingPx = horizontalPaddingDp * density
    val verticalPaddingPx = verticalPaddingDp * density

    val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = textColor.toArgb() // Исправление: используем .toArgb()
        textSize = textSizePx
        textAlign = Paint.Align.CENTER
        typeface = android.graphics.Typeface.DEFAULT_BOLD // Делаем текст жирным
    }

    val textBounds = Rect()
    textPaint.getTextBounds(text, 0, text.length, textBounds)

    val iconWidth = icon.width
    val iconHeight = icon.height
    val textWidth = textBounds.width()
    val textHeight = textBounds.height()

    val totalContentWidth = maxOf(iconWidth.toFloat(), textWidth.toFloat())
    val totalContentHeight = iconHeight + textHeight

    val totalWidth = (totalContentWidth + 2 * horizontalPaddingPx).toInt()
    val totalHeight = (totalContentHeight + 3 * verticalPaddingPx).toInt()

    val bitmap = createBitmap(totalWidth, totalHeight)
    val canvas = Canvas(bitmap)

    canvas.drawBitmap(icon, (totalWidth - iconWidth) / 2f, verticalPaddingPx, null)

    canvas.drawText(
        text,
        totalWidth / 2f,
        (verticalPaddingPx + iconHeight + textHeight + verticalPaddingPx),
        textPaint
    )

    return bitmap
}
