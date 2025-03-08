package com.murosar.kmp.dataconsuming.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

const val DATABASE_NAME = "TextTransformationDatabase.db"

@Database(
    entities = [TextTransformationEntity::class],
    version = 1
)
@ConstructedBy(MyDatabaseCtor::class)
abstract class TextTransformationDatabase : RoomDatabase() {

    abstract fun textTransformationDao(): TextTransformationDao
}

expect object MyDatabaseCtor : RoomDatabaseConstructor<TextTransformationDatabase>
