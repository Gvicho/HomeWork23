package com.example.homework21.di


import com.example.homework21.data.local.dao.ShmotkebisDao
import com.example.homework21.data.remote.service.ShmotkebisService
import com.example.homework21.data.repository.NetworkRepositoryImpl
import com.example.homework21.data.repository.ShmotkebisRepositoryImpl
import com.example.homework21.domain.repository.ShmotkebiNetworkRepository
import com.example.homework21.domain.repository.ShmotkebisRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideShmotkebisNetworkRepository(
        shmotkebisService: ShmotkebisService
    ): ShmotkebiNetworkRepository {
        return NetworkRepositoryImpl(
            shmotkebisService = shmotkebisService
        )
    }

    @Singleton
    @Provides
    fun provideShmotkebisRepository(
        shmotkebisDao: ShmotkebisDao,
    ): ShmotkebisRepository {
        return ShmotkebisRepositoryImpl(
            shmotkebisDao = shmotkebisDao
        )
    }

}