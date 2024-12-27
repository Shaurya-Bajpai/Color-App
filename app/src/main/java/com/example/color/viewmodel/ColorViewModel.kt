package com.example.color.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.color.database.ColorEntity
import com.example.color.database.ColorRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ColorViewModel(private val repository: ColorRepository) : ViewModel() {

    val allColors: StateFlow<List<ColorEntity>> = repository.allColors
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val pendingSyncCount: StateFlow<Int> = repository.allColors
        .map { colors -> colors.count { !it.isSynced } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    fun addColor() {
        viewModelScope.launch {
            val newColor = String.format(
                "#%06X",
                (0xFFFFFF and (Math.random() * 0xFFFFFF).toInt())
            )
            repository.addColor(color = newColor, timestamp = System.currentTimeMillis())
        }
    }

    fun syncColors() {
        viewModelScope.launch {
            repository.syncColors()
        }
    }
}
