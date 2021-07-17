package com.example.imagefilter.depedencyinjection

import com.example.imagefilter.repositories.EditImageRepository
import com.example.imagefilter.repositories.EditImageRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModul= module {
    factory <EditImageRepository>{EditImageRepositoryImpl(androidContext()) }
}