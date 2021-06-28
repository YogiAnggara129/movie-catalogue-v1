package com.anggasaraya.moviecatalogue.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity

@Database(entities = [MovieEntity::class, TVShowEntity::class], version = 1, exportSchema = false)
abstract class CatalogueDatabase: RoomDatabase() {
    abstract fun academyDao(): CatalogueDao

    companion object {

        @Volatile
        private var INSTANCE: CatalogueDatabase? = null

        fun getInstance(context: Context): CatalogueDatabase {
            if (INSTANCE == null) {
                synchronized(CatalogueDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            CatalogueDatabase::class.java, "FavoriteCatalogue.db")
                            .build()
                    }
                }
            }
            return INSTANCE as CatalogueDatabase
        }
    }
}