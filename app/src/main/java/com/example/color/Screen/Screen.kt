package com.example.color.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.color.viewmodel.ColorViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ColorAppScreen(viewModel: ColorViewModel) {
    val colors by viewModel.allColors.collectAsState()
    val pendingSyncCount by viewModel.pendingSyncCount.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                pendingSync = pendingSyncCount,
                onSyncClick = { viewModel.syncColors() }
            )
        },
        floatingActionButton = {
            AddColorButton(onAddClick = { viewModel.addColor() })
        }
    ) { paddingValues ->
        ColorGrid(
            colors = colors,
            modifier = Modifier.padding(paddingValues)
        )
    }
}