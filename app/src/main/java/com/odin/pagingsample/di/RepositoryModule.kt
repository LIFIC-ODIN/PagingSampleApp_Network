package com.odin.pagingsample.di

import com.odin.pagingsample.data.ImageDao
import com.odin.pagingsample.network.KakaoService
import com.odin.pagingsample.repository.ImageRepository
import com.odin.pagingsample.repository.ImageRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideImageRepository(
        imageDao: ImageDao,
        kakaoService: KakaoService
    ): ImageRepository = ImageRepositoryImpl(imageDao, kakaoService)

}