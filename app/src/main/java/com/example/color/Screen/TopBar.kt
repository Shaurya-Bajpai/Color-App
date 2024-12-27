package com.example.color.Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.color.R
import com.example.color.ui.theme.FloatColor
import com.example.color.ui.theme.FloatTextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(pendingSync: Int, onSyncClick: () -> Unit) {
    TopAppBar(
        title = { Text("Color App", color = Color.White, fontWeight = FontWeight.Bold) },
        actions = {
            TextButton(
                onClick = onSyncClick,
                colors = ButtonDefaults.buttonColors(FloatColor),
                shape = CircleShape,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(pendingSync.toString(), fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(horizontal = 10.dp), color = Color.White)
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_sync_24),
                        contentDescription = "Sync",
                        tint = FloatTextColor
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = FloatTextColor),
    )
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewTopBar() {
//    TopBar(12,{})
//}