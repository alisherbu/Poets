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
        Store<Intent, State, Label> by storeFactory.create(
            name = "MainStore",
            initialState = State(false),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = this::ExecutorImpl,
            reducer = ReducerImpl
        ) {}


    private sealed interface Message

    private inner class ExecutorImpl :
        CoroutineExecutor<Intent, Unit, State, Message, Label>(mainContext) {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.MenuItemClicked -> publish(Label.MenuItemClicked(intent.id))
                Intent.NavigationIconClicked -> publish(Label.NavigationIconClicked)
            }
        }
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State {
            return this
        }

    }
}
