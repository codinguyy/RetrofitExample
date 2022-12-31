package app.codinguyy.retrofitexample.di

import app.codinguyy.retrofitexample.data.RetrofitBuilder
import app.codinguyy.retrofitexample.repository.Repository
import app.codinguyy.retrofitexample.ui.firstfragment.FirstFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel { FirstFragmentViewModel(get()) }
}

val repositories = module {
    single { Repository(get()) }
    single { RetrofitBuilder() }
}
