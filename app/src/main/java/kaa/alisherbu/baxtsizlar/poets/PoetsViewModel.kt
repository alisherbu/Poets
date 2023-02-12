package kaa.alisherbu.baxtsizlar.poets

import android.util.Log
import androidx.lifecycle.ViewModel
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.github.terrakok.cicerone.Router
import kaa.alisherbu.baxtsizlar.Screen
import kaa.alisherbu.baxtsizlar.navigation.LocalCiceroneHolder
import kaa.alisherbu.baxtsizlar.navigation.RouterProvider

class PoetsViewModel(
    private val poetsStore: PoetsStore,
    private val dispatchers: PoetDispatchers,
    private val localCiceroneHolder: LocalCiceroneHolder,
) : ViewModel(), RouterProvider {
    private val cicerone = localCiceroneHolder.getCicerone("outside")
    override val router: Router get() = cicerone.router

    fun onViewCreated(view: PoetsView, viewLifecycle: Lifecycle) {
        bind(viewLifecycle, BinderLifecycleMode.CREATE_DESTROY, dispatchers.unconfined) {
            view.events bindTo poetsStore
        }

        bind(viewLifecycle, BinderLifecycleMode.START_STOP, dispatchers.unconfined) {
            poetsStore.states bindTo view
            poetsStore.labels.bindTo { view.handleLabel(it) }
        }
    }

    override fun onCleared() {
        poetsStore.dispose()
        super.onCleared()
    }

    private fun PoetsView.handleLabel(label: Label) {
        when (label) {
            is Label.Navigated -> router.navigateTo(Screen.Biography())
        }
    }

    fun onBackPressed() {
        router.exit()
    }
}
