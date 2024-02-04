package com.example.homework21.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShmotkebisEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val cover:String,
    val price:String,
    val title:String,
    val favorite:Boolean,
    val category: String // new field
) {
}