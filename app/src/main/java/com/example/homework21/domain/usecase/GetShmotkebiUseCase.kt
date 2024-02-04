package com.example.homework21.domain.usecase


import com.example.homework21.domain.is_online_checker.NetworkChecker
import com.example.homework21.domain.model.ShmotkebiAndCategorys
import com.example.homework21.domain.repository.ShmotkebisRepository
import com.example.homework21.domain.wrapper.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetShmotkebiUseCase @Inject constructor(
    private val shmotkebisRepository: ShmotkebisRepository,
    private val getShmotkebiFromNetworkUseCase:GetShmotkebiFromNetworkUseCase ,
    private val saveShmotkebiInDBUseCase:SaveShmotkebiInDBUseCase,
    private val networkChecker: NetworkChecker
){
    suspend operator fun invoke(): Flow<ResultWrapper<ShmotkebiAndCategorys>> = flow {

        emit(ResultWrapper.Loading(true))

        if(networkChecker.isOnline()){
            getShmotkebiFromNetworkUseCase().collect(){
                when(it){
                    is ResultWrapper.Error -> {
                        emit(ResultWrapper.Error(it.errorMessage ?: "Empty Error"))
                    }
                    is ResultWrapper.Loading -> {
                        // nothing will hapen here
                    }
                    is ResultWrapper.Success -> {
                        saveShmotkebiInDBUseCase(it.data!!) // if it is success data will never be null
                    }
                }
            }
        }
        val shmotkebi = shmotkebisRepository.getShmotkebi()

        val shmotkebiAndCategorys = withContext(Dispatchers.Default){
            val categorys =shmotkebi.map { it.category }+"All"
            ShmotkebiAndCategorys(shmotkebi,categorys.toSet().toList().sorted())
        }

        emit(ResultWrapper.Success(shmotkebiAndCategorys))

        emit(ResultWrapper.Loading(false))
    }
}