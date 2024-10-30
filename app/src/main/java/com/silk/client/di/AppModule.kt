package com.silk.client.di

import com.silk.client.data.remote.ApiConfig
import com.silk.client.data.repository.login.LoginRepository
import com.silk.client.data.repository.login.LoginRepositoryImpl
import com.silk.client.ui.viewmodels.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { ApiConfig.createApiService() }
}

val repositoryModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
}