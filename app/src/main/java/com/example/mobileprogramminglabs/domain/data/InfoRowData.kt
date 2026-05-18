package com.example.mobileprogramminglabs.domain.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

data class InfoRowData(
    val title: String,
    val additionalInfo: String? = null,
    val imageVector: ImageVector? = Icons.Default.Star
)
