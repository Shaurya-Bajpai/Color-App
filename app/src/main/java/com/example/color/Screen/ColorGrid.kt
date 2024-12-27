package com.example.color.Screen

import android.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.color.database.ColorEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ColorGrid(colors: List<ColorEntity>, modifier: Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(14.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(colors) { color ->
            ColorCard(color)
        }
    }
}


@Composable
fun ColorCard(color: ColorEntity) {
    Card(
        modifier = Modifier.aspectRatio(1.3f).padding(1.dp),
        colors = CardDefaults.cardColors(Color(Color.parseColor(color.color))),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = color.color,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 16.sp,
                    color = White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Divider(
                    color = White,
                    modifier = Modifier.fillMaxWidth(0.6f),
                    thickness = 1.dp
                )
            }

            Column(
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Created at",
                    fontSize = 14.sp,
                    color = White,
                    modifier = Modifier.padding(start = 8.dp)
                )
                Text(
                    text = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(color.timestamp)),
                    fontSize = 14.sp,
                    color = White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewColorCard() {
    ColorCard(ColorEntity(id = 0, color = "#FFAABB", timestamp = "12/05/2023".toLong()))
}