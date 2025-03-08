package com.murosar.kmp.dataconsuming.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TextTransformationDao {

    @Upsert
    suspend fun upsert(textTransformationEntity: TextTransformationEntity)

    @Delete
    suspend fun delete(textTransformationEntity: TextTransformationEntity)

    @Query("SELECT * FROM TextTransformationEntity")
    fun getAllTransformations(): Flow<List<TextTransformationEntity>>

}