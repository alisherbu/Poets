package kaa.alisherbu.baxtsizlar.poets

import com.arkivanov.mvikotlin.core.store.Store
import kaa.alisherbu.baxtsizlar.poets.PoetsStore.Intent
import kaa.alisherbu.baxtsizlar.poets.PoetsStore.State

interface PoetsStore : Store<Intent, State, Nothing> {
    sealed interface Intent
    data class State(
        val poets: List<Poet> = emptyList(),
    )
}
