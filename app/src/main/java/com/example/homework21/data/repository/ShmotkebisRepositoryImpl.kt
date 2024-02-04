package com.example.homework21.data.repository


import com.example.homework21.data.local.dao.ShmotkebisDao
import com.example.homework21.data.local.mapper.toDataEntity
import com.example.homework21.data.local.mapper.toDomain
import com.example.homework21.domain.model.Shmotkebi
import com.example.homework21.domain.repository.ShmotkebisRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShmotkebisRepositoryImpl @Inject constructor(
    private val shmotkebisDao: ShmotkebisDao,
):ShmotkebisRepository {
    override suspend fun getShmotkebi(): List<Shmotkebi>{

        val shmotkebi = shmotkebisDao.getAllShmotkebi()
        val shmotkebiMappedToDomain = withContext(Dispatchers.Default){
            shmotkebi.map { it.toDomain() }
        }
        return shmotkebiMappedToDomain
    }

    override suspend fun saveShmotkebi(smotkebi: List<Shmotkebi>) {
        val shmotkebiMappedToDataEntity = withContext(Dispatchers.Default){
            smotkebi.map { it.toDataEntity() }
        }
        shmotkebisDao.insertAll(shmotkebiMappedToDataEntity)
    }

    override suspend fun filterShmotkebi(category: String): List<Shmotkebi>{
        val shmotkebi =  withContext(Dispatchers.Default){
            shmotkebisDao.getByCategory(category).map { it.toDomain() }
        }
        return shmotkebi
    }


}