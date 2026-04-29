package com.example.mobileprogramminglabs.presentation.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.DeepTealDark
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe
import com.example.mobileprogramminglabs.presentation.ui.components.ShortcutCard
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.screens.error.ErrorScreen
import com.example.mobileprogramminglabs.presentation.ui.screens.loading.LoadingScreen
import com.example.mobileprogramminglabs.presentation.ui.util.ScreenShortcutData
import com.example.mobileprogramminglabs.presentation.view_model.home.HomeShortcutNavigationEvent
import com.example.mobileprogramminglabs.presentation.view_model.home.HomeShortcutUiState
import com.example.mobileprogramminglabs.presentation.view_model.home.HomeShortcutViewModel

@Composable
fun HomeShortcutScreen(
    viewModel: HomeShortcutViewModel,
    onScreenClick: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val searchTip by viewModel.searchTip.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.navigationEvent.collect { event ->
            when (event) {
                HomeShortcutNavigationEvent.Navigate -> Unit
                HomeShortcutNavigationEvent.NavigateBack -> Unit
            }
        }
    }

    when (val state = uiState) {
        is HomeShortcutUiState.Loading -> {
            LoadingScreen()
        }

        is HomeShortcutUiState.Error -> {
            ErrorScreen(
                message = state.message,
                onRetryClick = { viewModel.resetUiState() }
            )
        }

        is HomeShortcutUiState.Success -> {
            HomeShortcutScreen(
                searchQuery = state.searchQuery,
                shortcuts = state.shortcuts,
                onSearchQueryChange = viewModel::onSearchQueryChange,
                onScreenClick = onScreenClick
            )
        }
        else -> {
            //no-op
        }
    }
}

@Composable
private fun HomeShortcutScreen(
    searchQuery: String,
    shortcuts: List<ScreenShortcutData>,
    onSearchQueryChange: (String) -> Unit,
    onScreenClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Title(
            title = "Home Dashboard",
            color = DeepTealDark
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = { Text("Search screens") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = DeepTeal
                )
            },
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = DeepTeal,
                unfocusedBorderColor = RosyTaupe,
                focusedLabelColor = DeepTeal,
                cursorColor = DeepTeal
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(shortcuts) { shortcut ->
                ShortcutCard(
                    shortcut = shortcut,
                    onClick = { onScreenClick(shortcut.title) }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeShortcutScreenPreview() {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    val shortcuts = listOf(
        ScreenShortcutData("Achievements", Icons.Default.Star, R.drawable.achievement),
        ScreenShortcutData("Add Quest", Icons.Default.Add, R.drawable.add_quests),
        ScreenShortcutData("Habits", Icons.Default.DateRange, R.drawable.habits),
        ScreenShortcutData("Quests", Icons.Default.Build, R.drawable.quests),
        ScreenShortcutData("Profile", Icons.Default.Star, R.drawable.achievement),
        ScreenShortcutData("Dashboard", Icons.Default.Build, R.drawable.quests),
        ScreenShortcutData("Habits", Icons.Default.DateRange, R.drawable.habits),
        ScreenShortcutData("Quests", Icons.Default.Build, R.drawable.quests),
        ScreenShortcutData("Profile", Icons.Default.Star, R.drawable.achievement),
        ScreenShortcutData("Dashboard", Icons.Default.Build, R.drawable.quests)
    )

    val filteredShortcuts = shortcuts.filter {
        it.title.contains(searchQuery, ignoreCase = true)
    }

    HomeShortcutScreen(
        searchQuery = searchQuery,
        shortcuts  = filteredShortcuts,
        onSearchQueryChange = { searchQuery = it },
        onScreenClick = {}
    )
}