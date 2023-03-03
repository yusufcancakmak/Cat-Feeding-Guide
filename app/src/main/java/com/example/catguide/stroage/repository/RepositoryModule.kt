package com.example.catguide.stroage.repository

import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideStroageRepository(firebaseStorage: FirebaseStorage):StroageRepository{
        return StroageRepositoryImpl(firebaseStorage)
    }


}