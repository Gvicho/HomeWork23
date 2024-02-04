package com.example.homework21.data.local.mapper

import com.example.homework21.data.local.model.ShmotkebisEntity
import com.example.homework21.domain.model.Shmotkebi

fun ShmotkebisEntity.toDomain():Shmotkebi{
    return Shmotkebi(
        id = id,
        cover = cover,
        price = price,
        favorite = favorite,
        title = title,
        category = category
    )
}

fun Shmotkebi.toDataEntity():ShmotkebisEntity{
    return ShmotkebisEntity(
        id = id,
        cover = cover,
        price = price,
        favorite = favorite,
        title = title,
        category = category
    )
}