package com.startzplay.di

import com.startzplay.repository.HomeRepo
import com.startzplay.repository.HomeRepoImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepoModule {

    @Provides
    fun provideAppRepository(homeRepo: HomeRepoImp): HomeRepo = homeRepo
}