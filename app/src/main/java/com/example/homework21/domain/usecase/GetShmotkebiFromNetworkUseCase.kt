package com.example.homework21.domain.usecase

import com.example.homework21.domain.model.Shmotkebi
import com.example.homework21.domain.repository.ShmotkebiNetworkRepository
import com.example.homework21.domain.wrapper.ResultWrapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShmotkebiFromNetworkUseCase@Inject constructor(
    private val shmotkebiNetworkRepository: ShmotkebiNetworkRepository
) {
    suspend operator fun invoke(): Flow<ResultWrapper<List<Shmotkebi>>> {
        return shmotkebiNetworkRepository.getShmotkebiFromServer()
    }
}