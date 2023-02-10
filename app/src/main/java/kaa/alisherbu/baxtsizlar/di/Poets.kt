package kaa.alisherbu.baxtsizlar.di

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import kaa.alisherbu.baxtsizlar.BuildConfig
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
    }
}
