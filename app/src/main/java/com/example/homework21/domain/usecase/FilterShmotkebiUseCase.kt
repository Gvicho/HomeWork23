package com.example.homework21.domain.usecase

import com.example.homework21.domain.model.Shmotkebi
import com.example.homework21.domain.repository.ShmotkebisRepository
import javax.inject.Inject

class FilterShmotkebiUseCase@Inject constructor(
    private val shmotkebisRepository: ShmotkebisRepository
) {
    suspend operator fun invoke(category:String): List<Shmotkebi> {
        return if(category == "All"){
            shmotkebisRepository.getShmotkebi()
        }else{
            shmotkebisRepository.filterShmotkebi(category)
        }
    }
}