package com.example.homework21.domain.usecase

import com.example.homework21.domain.model.Shmotkebi
import com.example.homework21.domain.repository.ShmotkebisRepository
import javax.inject.Inject

class SaveShmotkebiInDBUseCase@Inject constructor(
    private val shmotkebisRepository: ShmotkebisRepository
){
    suspend operator fun invoke(shmotkebi: List<Shmotkebi>){
        shmotkebisRepository.saveShmotkebi(shmotkebi)
    }
}