package com.example.mobileprogramminglabs.model.di

import android.content.Context
import com.example.mobileprogramminglabs.domain.repository.AuthRepository
import com.example.mobileprogramminglabs.domain.repository.QuestRepository
import com.example.mobileprogramminglabs.model.repository.auth.AuthRepositoryImpl
import com.example.mobileprogramminglabs.model.repository.quest.QuestRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore =
        FirebaseFirestore.getInstance()


    @Provides
    @Singleton
    fun provideQuestRepository(
        firestore: FirebaseFirestore,
        @ApplicationContext context: Context
    ): QuestRepository {
        return QuestRepositoryImpl(
            firestore = firestore,
            context = context
        )
    }
    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuth: FirebaseAuth
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }
}