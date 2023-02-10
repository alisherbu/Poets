package kaa.alisherbu.baxtsizlar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.logging.store.LoggingStoreFactory
import com.arkivanov.mvikotlin.main.store.DefaultStoreFactory
import com.arkivanov.mvikotlin.timetravel.store.TimeTravelStoreFactory
import kaa.alisherbu.baxtsizlar.poets.Event
import kaa.alisherbu.baxtsizlar.poets.Poet
import kaa.alisherbu.baxtsizlar.poets.PoetsStore


fun Fragment.addFragment(fragment: Fragment, frameId: Int) {
    childFragmentManager.inTransaction { add(frameId, fragment) }
}

fun Fragment.replaceFragment(fragment: Fragment, frameId: Int) {
    childFragmentManager.inTransaction { replace(frameId, fragment) }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}

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


