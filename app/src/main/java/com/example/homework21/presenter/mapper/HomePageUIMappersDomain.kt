package com.example.homework21.presenter.mapper

import com.example.homework21.domain.model.ShmotkebiAndCategorys
import com.example.homework21.presenter.model.HomePageUI

fun ShmotkebiAndCategorys.toUI():HomePageUI{
    return HomePageUI(
        categoryList = categorys,
        shmotkebi = shmotkebi.map { it.toUI() }
    )
}