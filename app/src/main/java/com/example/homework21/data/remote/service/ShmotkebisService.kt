package com.example.homework21.data.remote.service

import com.example.homework21.data.remote.model.ShmotkebisDto
import retrofit2.Response
import retrofit2.http.GET

interface ShmotkebisService {

    @GET("df8d4951-2757-45aa-8f60-bf1592a090ce")
    suspend fun getShmotkebi(): Response<List<ShmotkebisDto>>

}