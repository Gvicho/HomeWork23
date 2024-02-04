package com.example.homework21.data.repository

import com.example.homework21.data.remote.mapper.toDomain
import com.example.homework21.data.remote.service.ShmotkebisService
import com.example.homework21.domain.model.Shmotkebi
import com.example.homework21.domain.repository.ShmotkebiNetworkRepository
import com.example.homework21.domain.wrapper.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val shmotkebisService: ShmotkebisService,
):ShmotkebiNetworkRepository {
    override suspend fun getShmotkebiFromServer(): Flow<ResultWrapper<List<Shmotkebi>>> = flow {
        try {
            val response = shmotkebisService.getShmotkebi()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val bodyMappedToLocal = withContext(Dispatchers.Default){
                    body.map { it.toDomain() }
                }
                emit(ResultWrapper.Success(bodyMappedToLocal))
            } else {
                emit(ResultWrapper.Error(response.errorBody()?.toString() ?: "Unknown Error"))
            }
        } catch (e: Exception) {
            emit(ResultWrapper.Error(e.toString()))
        }
    }
}