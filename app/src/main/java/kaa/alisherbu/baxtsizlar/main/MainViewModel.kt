package kaa.alisherbu.baxtsizlar.main

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
import kaa.alisherbu.baxtsizlar.poets.PoetDispatchers

class MainViewModel(
    private val mainStore: MainStore,
    private val dispatchers: PoetDispatchers,
    private val ciceroneHolder: LocalCiceroneHolder,
) : ViewModel(), RouterProvider {
    private val cicerone get() = ciceroneHolder.getCicerone("main")
    override val router: Router get() = cicerone.router
    private var lastSelectedMenuItemId = 0

    fun onViewCreated(view: MainView, viewLifecycle: Lifecycle) {
        bind(viewLifecycle, BinderLifecycleMode.CREATE_DESTROY, dispatchers.unconfined) {
            view.events bindTo mainStore
        }

        bind(viewLifecycle, BinderLifecycleMode.START_STOP, dispatchers.unconfined) {
            mainStore.states bindTo view
            mainStore.labels.bindTo { view.handleLabel(it) }
        }
    }

    private fun MainView.handleLabel(label: Label) {
        when (label) {
            is Label.MenuItemClicked -> selectMenuItem(label.id)
            Label.NavigationIconClicked -> openDrawer()
        }
    }

    private fun selectMenuItem(id: Int) {
        if (id == lastSelectedMenuItemId) return
        when (id) {
            0 -> router.replaceScreen(Screen.Poets())
            1 -> router.replaceScreen(Screen.Favorites())
        }
        lastSelectedMenuItemId = id
    }

    override fun onCleared() {
        mainStore.dispose()
        super.onCleared()
    }

    fun onBackPressed() {
        router.exit()
    }
}
