package com.example.mobileprogramminglabs.domain.data

data class ProfileData(
    val name: String,
    val levelNo: String,
    val levelDescription: String,
    val profileStats: List<InfoRowData>
)
