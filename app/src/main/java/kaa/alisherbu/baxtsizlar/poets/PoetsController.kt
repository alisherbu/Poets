package kaa.alisherbu.baxtsizlar.poets

import android.util.Log
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.states
import com.github.terrakok.cicerone.Router
import kaa.alisherbu.baxtsizlar.Screen
import kaa.alisherbu.baxtsizlar.navigation.LocalCiceroneHolder
import kaa.alisherbu.baxtsizlar.navigation.RouterProvider

class PoetsController(
    private val dispatchers: PoetDispatchers,
    private val localCiceroneHolder: LocalCiceroneHolder,
    private val storeFactory: StoreFactory,
    instanceKeeper: InstanceKeeper,
    lifecycle: Lifecycle,
) : RouterProvider {

    private val poetsStore = instanceKeeper.getStore {
        PoetsStoreFactory(
            storeFactory,
            dispatchers.main,
            dispatchers.io
        ).create()
    }

    init {
        lifecycle.doOnDestroy { poetsStore.dispose() }
    }

    private val cicerone = localCiceroneHolder.getCicerone("main")
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

    private fun PoetsView.handleLabel(label: Label) {
        Log.d("ViewModelLog", "handleLabel")
        when (label) {
            is Label.Navigated -> localCiceroneHolder.getCicerone("outside").router.navigateTo(
                Screen.Biography()
            )
        }
    }

    fun onBackPressed() {
        router.exit()
    }
}
