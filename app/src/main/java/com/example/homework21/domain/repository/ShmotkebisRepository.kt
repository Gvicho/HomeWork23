package com.example.homework21.domain.repository


import com.example.homework21.domain.model.Shmotkebi


interface ShmotkebisRepository {
    suspend fun getShmotkebi(): List<Shmotkebi>

    suspend fun saveShmotkebi(smotkebi:List<Shmotkebi>)

    suspend fun filterShmotkebi(category:String):List<Shmotkebi>
}