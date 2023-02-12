package kaa.alisherbu.baxtsizlar.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import kaa.alisherbu.baxtsizlar.BuildConfig
import kaa.alisherbu.baxtsizlar.DefaultDispatchers
import kaa.alisherbu.baxtsizlar.bio.BiographyViewModel
import kaa.alisherbu.baxtsizlar.favorites.FavoritesViewModel
import kaa.alisherbu.baxtsizlar.main.MainStoreFactory
import kaa.alisherbu.baxtsizlar.main.MainViewModel
import kaa.alisherbu.baxtsizlar.poets.PoetsStoreFactory
import kaa.alisherbu.baxtsizlar.poets.PoetsViewModel
import kaa.alisherbu.baxtsizlar.root.RootViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

object Poets {
    val module = module {
        single {
            if (BuildConfig.DEBUG) {
                LoggingStoreFactory(delegate = TimeTravelStoreFactory())
            } else {
                DefaultStoreFactory()
            }

        } bind StoreFactory::class

        factory {
            PoetsStoreFactory(
                get(),
                DefaultDispatchers.main,
                DefaultDispatchers.io
            ).create()
        }
        viewModel { PoetsViewModel(get(), DefaultDispatchers, get()) }

        factory {
            MainStoreFactory(
                get(),
                DefaultDispatchers.main,
                DefaultDispatchers.io
            ).create()
        }

        viewModel { MainViewModel(get(), DefaultDispatchers, get()) }

        viewModel { BiographyViewModel(get()) }
        viewModel { FavoritesViewModel(get()) }
        viewModel { RootViewModel(get()) }
    }
}
