package com.example.homework21.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.homework21.data.local.model.ShmotkebisEntity

@Dao
interface ShmotkebisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(shmotkebisList: List<ShmotkebisEntity>)

    @Query("DELETE FROM shmotkebisentity")
    suspend fun clearTable()

    @Query("SELECT * FROM shmotkebisentity")
    suspend fun getAllShmotkebi(): List<ShmotkebisEntity>

    @Query("SELECT * FROM ShmotkebisEntity WHERE category = :category")
    fun getByCategory(category: String): List<ShmotkebisEntity>
}