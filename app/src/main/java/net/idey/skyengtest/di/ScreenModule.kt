package net.idey.skyengtest.di

import net.idey.skyengtest.data.repository.MainRepository
import net.idey.skyengtest.domain.interactor.MainInteractor
import net.idey.skyengtest.presentation.search.SearchViewModel
import net.idey.skyengtest.presentation.word.WordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val screenModule = module {
    single { MainRepository(get()) }
    factory { MainInteractor(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { WordViewModel(get()) }
}