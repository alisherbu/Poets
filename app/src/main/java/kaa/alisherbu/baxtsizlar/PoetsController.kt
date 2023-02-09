package kaa.alisherbu.baxtsizlar

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.mvikotlin.core.binder.BinderLifecycleMode
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.bind
import com.arkivanov.mvikotlin.extensions.coroutines.events
import com.arkivanov.mvikotlin.extensions.coroutines.states
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.mapNotNull

class PoetsController(
    private val storeFactory: StoreFactory,
    lifecycle: Lifecycle,
    instanceKeeper: InstanceKeeper,
    private val dispatchers: PoetDispatchers,
) {
    private val poetsStore = instanceKeeper.getStore {
        PoetsStoreFactory(storeFactory, dispatchers.main, dispatchers.io).create()
    }

    fun onViewCreated(view: PoetsListView, viewLifecycle: Lifecycle) {
        bind(viewLifecycle, BinderLifecycleMode.CREATE_DESTROY, dispatchers.unconfined) {
            view.events.mapNotNull(eventToListIntent) bindTo poetsStore
        }

        bind(viewLifecycle, BinderLifecycleMode.START_STOP, dispatchers.unconfined){
            poetsStore.states.mapNotNull(statesToList) bindTo view
        }
    }
}
