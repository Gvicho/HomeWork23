package com.example.homework21.presenter.state

import com.example.homework21.presenter.model.HomePageUI

data class HomePageState(
    val isLoading:Boolean = false,
    val isSuccess: HomePageUI? = null,
    val errorMessage:String? = null,
) {
}