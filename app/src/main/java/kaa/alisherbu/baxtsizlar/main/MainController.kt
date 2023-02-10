package kaa.alisherbu.baxtsizlar.main

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kaa.alisherbu.baxtsizlar.poets.PoetDispatchers
import kotlinx.coroutines.flow.mapNotNull

class MainController(
    private val storeFactory: StoreFactory,
    instanceKeeper: InstanceKeeper,
    private val dispatchers: PoetDispatchers,
) {
    private val mainStore = instanceKeeper.getStore {
        MainStoreFactory(storeFactory, dispatchers.main, dispatchers.io).create()
    }

    fun onViewCreated(view: MainView, viewLifecycle: Lifecycle) {
        bind(viewLifecycle, BinderLifecycleMode.CREATE_DESTROY, dispatchers.unconfined) {
            view.events.bindTo { event ->
                when (event) {
                    is Event.MenuItemClicked -> onMenuItemClick?.invoke(event.id)
                }
            }
        }
        bind(viewLifecycle, BinderLifecycleMode.START_STOP, dispatchers.unconfined) {
            mainStore.states.mapNotNull { Model(it.isOpen) } bindTo view
        }
    }

    private var onMenuItemClick: ((id: Int) -> Unit)? = null
    fun setNavigationItemSelectedListener(onMenuItemClick: ((id: Int) -> Unit)?) {
        this.onMenuItemClick = onMenuItemClick
    }
}
