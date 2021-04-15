package com.odin.pagingsample.di

import android.app.Application
import androidx.room.Room
import com.odin.pagingsample.data.AppDatabase
import com.odin.pagingsample.data.ImageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "kakao-image-db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideImageDao(appDatabase: AppDatabase) : ImageDao =
        appDatabase.imageDao()
}