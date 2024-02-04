package com.example.homework21.presenter.event

sealed class HomePageEvent {
    data object LoadShmotkebi:HomePageEvent()
    data object ResetErrorMessageToNull: HomePageEvent()
    data class FilterShmotkebi(val category:String,val lastSelectedCategory:Int):HomePageEvent()
}