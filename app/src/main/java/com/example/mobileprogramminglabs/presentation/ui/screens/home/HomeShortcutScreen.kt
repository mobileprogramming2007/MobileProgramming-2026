package com.example.mobileprogramminglabs.presentation.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.DeepTealDark
import com.example.mobileprogramminglabs.presentation.theme.RosyTaupe
import com.example.mobileprogramminglabs.presentation.ui.components.ShortcutCard
import com.example.mobileprogramminglabs.presentation.ui.components.Title
import com.example.mobileprogramminglabs.presentation.ui.util.ScreenShortcutData

@Composable
fun HomeShortcutScreen() {
    var searchQuery by rememberSaveable { mutableStateOf("") }

    val shortcuts = listOf(
        ScreenShortcutData("Achievements", Icons.Default.Star, R.drawable.achievement),
        ScreenShortcutData("Add Quest", Icons.Default.Add, R.drawable.add_quests),
        ScreenShortcutData("Habits", Icons.Default.DateRange, R.drawable.habits),
        ScreenShortcutData("Quests", Icons.Default.Build, R.drawable.quests),
    )

    val filteredShortcuts = shortcuts.filter {
        it.title.contains(searchQuery, ignoreCase = true)
    }

    val shortcutRows = filteredShortcuts.chunked(2)

    HomeShortcutScreen(
        searchQuery = searchQuery,
        shortcutRows = shortcutRows,
        onSearchQueryChange = { searchQuery = it },
        onScreenClick = {}
    )
}

@Composable
private fun HomeShortcutScreen(
    searchQuery: String,
    shortcutRows: List<List<ScreenShortcutData>>,
    onSearchQueryChange: (String) -> Unit,
    onScreenClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Title(title = "Home Dashboard", color = DeepTealDark)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            shortcutRows.forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    rowItems.forEach { shortcut ->
                        Box(modifier = Modifier.weight(1f)) {
                            ShortcutCard(
                                shortcut = shortcut,
                                onClick = { onScreenClick(shortcut.title) }
                            )
                        }
                    }

                    if (rowItems.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
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
    )

    val filteredShortcuts = shortcuts.filter {
        it.title.contains(searchQuery, ignoreCase = true)
    }

    val shortcutRows = filteredShortcuts.chunked(2)

    HomeShortcutScreen(
        searchQuery = searchQuery,
        shortcutRows = shortcutRows,
        onSearchQueryChange = { searchQuery = it },
        onScreenClick = {},
    )
}