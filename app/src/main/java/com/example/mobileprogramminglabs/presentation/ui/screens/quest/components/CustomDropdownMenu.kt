package com.example.mobileprogramminglabs.presentation.ui.screens.quest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobileprogramminglabs.R
import com.example.mobileprogramminglabs.presentation.theme.AliceBlue
import com.example.mobileprogramminglabs.presentation.theme.DeepTeal
import com.example.mobileprogramminglabs.presentation.theme.MobileProgrammingLabsTheme

@Composable
fun CustomDropdownMenu(
    selectedItem: String,
    items: List<String>,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(dimensionResource(R.dimen.size_small)))
                .background(color = AliceBlue)
                .clickable { onExpandedChange(!expanded) }
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = selectedItem,
                color = DeepTeal,
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = stringResource(R.string.category_dropdown),
                tint = DeepTeal
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = item,
                            color = DeepTeal
                        )
                    },
                    onClick = {
                        onItemSelected(item)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun CustomDropdownMenuPreview() {
    MobileProgrammingLabsTheme{
        var selectedCategory by rememberSaveable { mutableStateOf("Select category") }
        var isCategoryExpanded by remember { mutableStateOf(false) }

        CustomDropdownMenu(
            selectedItem = selectedCategory,
            items = listOf("Health", "Study", "Fitness", "Productivity"),
            expanded = isCategoryExpanded,
            onExpandedChange = { isCategoryExpanded = it },
            onItemSelected = {
                selectedCategory = it
                isCategoryExpanded = false
            }
        )
    }
}