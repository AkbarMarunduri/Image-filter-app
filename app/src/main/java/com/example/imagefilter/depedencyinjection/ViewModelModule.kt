package com.example.imagefilter.depedencyinjection

import com.example.imagefilter.viewmodels.EditImageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
    viewModel { EditImageViewModel(editimagerepository = get()) }
}