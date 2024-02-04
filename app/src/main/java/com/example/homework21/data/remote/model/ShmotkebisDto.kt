package com.example.homework21.data.remote.model

data class ShmotkebisDto(
    val id: Int = 0,
    val cover:String,
    val price:String,
    val title:String,
    val favorite:Boolean,
    val category: String // new field
) {
}