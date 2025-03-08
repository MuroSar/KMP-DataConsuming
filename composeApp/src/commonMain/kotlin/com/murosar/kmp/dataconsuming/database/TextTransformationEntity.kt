package com.murosar.kmp.dataconsuming.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TextTransformationEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val uncensoredText: String,
    val censoredText: String,
)
