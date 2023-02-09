package kaa.alisherbu.baxtsizlar

import com.arkivanov.mvikotlin.core.store.Store
import kaa.alisherbu.baxtsizlar.PoetsStore.Intent
import kaa.alisherbu.baxtsizlar.PoetsStore.State

interface PoetsStore : Store<Intent, State, Nothing> {
    sealed interface Intent
    data class State(
        val poets: List<Poet> = emptyList(),
    )
}
