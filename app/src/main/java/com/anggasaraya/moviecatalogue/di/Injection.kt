package com.anggasaraya.moviecatalogue.di

import android.content.Context
import com.anggasaraya.moviecatalogue.data.CatalogueRepository
import com.anggasaraya.moviecatalogue.data.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): CatalogueRepository {
        val remoteRepository = RemoteDataSource().getInstance()
        return CatalogueRepository.getInstance(remoteRepository)
    }
}