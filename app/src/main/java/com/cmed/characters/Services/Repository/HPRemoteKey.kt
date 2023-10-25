package com.cmed.characters.Services.Repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class HPRemoteKey (
    @PrimaryKey(autoGenerate = false)
    val id: String,

    val prevPage: Int?,
    val nextPage: Int?
)