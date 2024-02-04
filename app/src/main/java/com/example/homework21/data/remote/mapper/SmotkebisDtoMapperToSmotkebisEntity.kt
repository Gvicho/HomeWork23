package com.example.homework21.data.remote.mapper

import com.example.homework21.data.remote.model.ShmotkebisDto
import com.example.homework21.domain.model.Shmotkebi

fun ShmotkebisDto.toDomain():Shmotkebi{
    return Shmotkebi(
        id = id,
        cover = cover,
        price = price,
        favorite = favorite,
        title = title,
        category = category
    )
}
