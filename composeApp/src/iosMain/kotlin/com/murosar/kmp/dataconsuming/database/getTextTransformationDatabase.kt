package com.murosar.kmp.dataconsuming.database

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSHomeDirectory

fun getTextTransformationDatabase(): TextTransformationDatabase {
    val dbFile = NSHomeDirectory() + "/$DATABASE_NAME"
    return Room.databaseBuilder<TextTransformationDatabase>(
        name = dbFile,
        //.instantiateImpl() will be generated by KSP when compile first time
//        factory = { TextTransformationDatabase::class.instantiateImpl() }
    )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}