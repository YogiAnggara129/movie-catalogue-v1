package com.anggasaraya.moviecatalogue.di

import android.content.Context
import com.anggasaraya.moviecatalogue.data.CatalogueRepository
import com.anggasaraya.moviecatalogue.data.local.LocalDataSource
import com.anggasaraya.moviecatalogue.data.local.room.CatalogueDatabase
import com.anggasaraya.moviecatalogue.data.remote.RemoteDataSource
import com.anggasaraya.moviecatalogue.helper.AppExecutors

object Injection {
    fun provideRepository(context: Context): CatalogueRepository {
        val database = CatalogueDatabase.getInstance(context)

        val remoteRepository = RemoteDataSource().getInstance()
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()
        return CatalogueRepository.getInstance(remoteRepository, localDataSource, appExecutors)
    }
}