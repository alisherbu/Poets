package kaa.alisherbu.baxtsizlar.main

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlin.coroutines.CoroutineContext

class MainStoreFactory(
    private val storeFactory: StoreFactory,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) {

    fun create(): MainStore = object : MainStore,
        Store<MainStore.Intent, MainStore.State, Nothing> by storeFactory.create(
            name = "MainStore",
            initialState = MainStore.State(false),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = this::ExecutorImpl,
            reducer = ReducerImpl
        ) {}


    private sealed interface Message {
        class Changed(val isOpen: Boolean) : Message
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<MainStore.Intent, Unit, MainStore.State, Message, Nothing>(mainContext) {
        override fun executeIntent(intent: MainStore.Intent, getState: () -> MainStore.State) {
            when (intent) {
                MainStore.Intent.NavigationIconClicked -> dispatch(Message.Changed(!getState().isOpen))
            }
        }
    }

    private object ReducerImpl : Reducer<MainStore.State, Message> {
        override fun MainStore.State.reduce(msg: Message): MainStore.State {
            return when (msg) {
                is Message.Changed -> copy(isOpen = msg.isOpen)
            }
        }

    }
}
