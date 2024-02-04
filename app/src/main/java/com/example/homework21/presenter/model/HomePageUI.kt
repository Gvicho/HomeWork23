package com.example.homework21.presenter.model

data class HomePageUI(
    val categoryList: List<String>,
    val shmotkebi: List<ShmotkebisUI>,
    val lastSelectedCategory:Int= 0
) {
}