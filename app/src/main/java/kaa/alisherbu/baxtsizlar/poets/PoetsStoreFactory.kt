package kaa.alisherbu.baxtsizlar.poets

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.SimpleBootstrapper
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PoetsStoreFactory(
    private val storeFactory: StoreFactory,
    private val mainContext: CoroutineContext,
    private val ioContext: CoroutineContext,
) {
    fun create(): PoetsStore =
        object : PoetsStore, Store<Intent, State, Label> by storeFactory.create(
            name = "PoetsStore",
            initialState = State(),
            bootstrapper = SimpleBootstrapper(Unit),
            executorFactory = this::ExecutorImpl,
            reducer = ReducerImpl,
        ) {}

    private sealed interface Message {
        class Loaded(val poets: List<Poet>) : Message
    }

    private inner class ExecutorImpl :
        CoroutineExecutor<Intent, Unit, State, Message, Label>(mainContext) {
        override fun executeAction(action: Unit, getState: () -> State) {
            scope.launch {
                val poets = withContext(ioContext) { listOf(Poet(0, "A"), Poet(1, "B")) }
                dispatch(Message.Loaded(poets))
            }
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            when(intent){
                is Intent.ItemClicked -> publish(Label.Navigated(intent.id))
            }
        }
    }

    private object ReducerImpl : Reducer<State, Message> {
        override fun State.reduce(msg: Message): State {
            return when (msg) {
                is Message.Loaded -> copy(poets = msg.poets)
            }
        }
    }

}
