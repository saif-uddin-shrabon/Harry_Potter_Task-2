package com.cmed.characters.Services.Model

import androidx.room.Entity

@Entity(tableName = "HarryPotter")
data class Wand(
    val core: String,
    val length: Double,
    val wood: String
)