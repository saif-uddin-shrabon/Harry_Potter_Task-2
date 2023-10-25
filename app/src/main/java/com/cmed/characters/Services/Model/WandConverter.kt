package com.cmed.characters.Services.Model

import androidx.room.TypeConverter
import com.google.gson.Gson

class WandConverter {
    @TypeConverter
    fun fromWand(wand: Wand): String {
        return Gson().toJson(wand)
    }

    @TypeConverter
    fun toWand(wandString: String): Wand {
        return Gson().fromJson(wandString, Wand::class.java)
    }
}