package com.cmed.characters.Services.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "HarryPotter")
data class responseDataItem(
    @PrimaryKey(autoGenerate = false)
    val actor: String,
    val alive: Boolean,
    @TypeConverters(StringListConverter::class)
    val alternate_actors: String,
    @TypeConverters(StringListConverter::class)
    val alternate_names: String,
    val ancestry: String,
    val dateOfBirth: String,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    val id: String,
    val image: String,
    val name: String,
    val patronus: String,
    val species: String,
//    @TypeConverters(WandConverter::class)
    val wand: Wand,
    val wizard: Boolean,
    val yearOfBirth: Int
)