package com.example.mobileprogramminglabs.presentation.view_model.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.ui.util.ScreenShortcutData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeShortcutViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<HomeShortcutUiState>(HomeShortcutUiState.Init)
    val uiState: StateFlow<HomeShortcutUiState> = _uiState.asStateFlow()

    private val _navigationEvent = Channel<HomeShortcutNavigationEvent>(Channel.BUFFERED)
    val navigationEvent = _navigationEvent.receiveAsFlow()

    private val _searchTip = MutableStateFlow("")
    val searchTip: StateFlow<String> = _searchTip.asStateFlow()


    private val allShortcuts = listOf(
        ScreenShortcutData("Achievements", Icons.Default.Star, R.drawable.achievement),
        ScreenShortcutData("Add Quest", Icons.Default.Add, R.drawable.add_quests),
        ScreenShortcutData("Habits", Icons.Default.DateRange, R.drawable.habits),
        ScreenShortcutData("Quests", Icons.Default.Build, R.drawable.quests),
        ScreenShortcutData("Profile", Icons.Default.Star, R.drawable.achievement),
        ScreenShortcutData("Dashboard", Icons.Default.Build, R.drawable.quests)
    )

    init {
        loadShortcuts()
    }

    private fun loadShortcuts() {
        viewModelScope.launch {
            _uiState.value = HomeShortcutUiState.Loading

            _uiState.value = HomeShortcutUiState.Success(
                searchQuery = "",
                shortcuts = allShortcuts
            )
        }
    }

    fun onSearchQueryChange(query: String) {
        val filteredShortcuts = allShortcuts.filter {
            it.title.contains(query, ignoreCase = true)
        }

        _uiState.value = HomeShortcutUiState.Success(
            searchQuery = query,
            shortcuts = filteredShortcuts
        )
    }

    private fun observeSearchTips(): Flow<String> = flow {
        emit("Try searching for Habits")
        delay(1000)

        emit("Try searching for Profile")
        delay(1000)

        emit("Try searching for Quests")
    }

    private fun startSearchTips() {
        viewModelScope.launch {
            observeSearchTips().collect { tip ->
                _searchTip.value = tip
            }
        }
    }

    fun resetUiState() {
        loadShortcuts()
    }
}