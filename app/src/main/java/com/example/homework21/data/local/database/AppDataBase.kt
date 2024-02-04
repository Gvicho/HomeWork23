package com.example.homework21.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework21.data.local.dao.ShmotkebisDao
import com.example.homework21.data.local.model.ShmotkebisEntity

@Database(entities = [ShmotkebisEntity::class], version = 2)
abstract class AppDataBase :RoomDatabase(){

    abstract fun shmotkebisDao(): ShmotkebisDao
}