package com.example.mobileprogramminglabs.presentation.ui.screens.profile.util

import com.example.mobileprogramminglabs.presentation.ui.util.InfoRowData

data class ProfileData(
    val name: String,
    val levelNo: String,
    val levelDescription: String,
    val profileStats: List<InfoRowData>,
    val additionalRows: List<InfoRowData>
)
