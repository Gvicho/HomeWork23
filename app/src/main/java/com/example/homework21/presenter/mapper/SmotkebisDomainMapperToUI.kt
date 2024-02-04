package com.example.homework21.presenter.mapper

import com.example.homework21.domain.model.Shmotkebi
import com.example.homework21.presenter.model.ShmotkebisUI

fun Shmotkebi.toUI():ShmotkebisUI{
    return ShmotkebisUI(
        id = id,
        cover = cover,
        price = price,
        favorite = favorite,
        title = title
    )
}