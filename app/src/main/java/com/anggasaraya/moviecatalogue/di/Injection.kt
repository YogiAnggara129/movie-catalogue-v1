package com.anggasaraya.moviecatalogue.di

import android.content.Context
import com.anggasaraya.moviecatalogue.data.CatalogueRepository

object Injection {
    fun provideRepository(context: Context): CatalogueRepository {
        return CatalogueRepository.getInstance()
    }
}