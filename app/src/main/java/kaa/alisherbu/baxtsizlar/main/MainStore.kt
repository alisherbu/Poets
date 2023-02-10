package kaa.alisherbu.baxtsizlar.main

import com.arkivanov.mvikotlin.core.store.Store

interface MainStore : Store<MainStore.Intent, MainStore.State, Nothing> {
    sealed interface Intent {
        object NavigationIconClicked : Intent
    }

    data class State(
        val isOpen: Boolean,
    )
}
