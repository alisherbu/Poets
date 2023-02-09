package kaa.alisherbu.baxtsizlar

import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory

val storeFactoryInstance: StoreFactory =
    if (BuildConfig.DEBUG) {
        LoggingStoreFactory(delegate = TimeTravelStoreFactory())
    } else {
        DefaultStoreFactory()
    }

internal val eventToListIntent: (Event) -> PoetsStore.Intent? =
    { event ->
        when (event) {
            is Event.ItemClicked -> null
        }
    }

internal val statesToList: (PoetsStore.State) -> List<Poet> =
    { listState -> listState.poets }


