package com.example.color

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.room.Room
import com.example.color.Screen.ColorAppScreen
import com.example.color.database.ColorDatabase
import com.example.color.database.ColorRepository
import com.example.color.ui.theme.ColorTheme
import com.example.color.viewmodel.ColorViewModel
import com.example.color.viewmodel.ColorViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel

class MainActivity : ComponentActivity() {
    lateinit var database: ColorDatabase
    lateinit var repository: ColorRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize Room Database
        database = Room.databaseBuilder(
            applicationContext,
            ColorDatabase::class.java,
            "color_database"
        ).build()

        repository = ColorRepository(database.colorDao())

        setContent {
            ColorTheme {
                val viewModel: ColorViewModel = viewModel(factory = ColorViewModelFactory(repository))
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ColorAppScreen(viewModel)
                }
            }
        }
    }
}