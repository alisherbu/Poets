package kaa.alisherbu.baxtsizlar.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arkivanov.essenty.instancekeeper.instanceKeeper
import com.arkivanov.essenty.lifecycle.essentyLifecycle
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import kaa.alisherbu.baxtsizlar.DefaultDispatchers
import kaa.alisherbu.baxtsizlar.R
import kaa.alisherbu.baxtsizlar.Screen
import org.koin.android.ext.android.inject


class MainFragment : Fragment(R.layout.fragment_main) {
    private var navigator: Navigator? = null
    private val navigatorHolder: NavigatorHolder by inject()
    private lateinit var mainController: MainController
    private val router: Router by inject()
    private val storeFactory: StoreFactory by inject()
    private var lastSelectedMenuItemId: Int = R.id.nav_poets
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigator = AppNavigator(requireActivity(), R.id.mainContainer, childFragmentManager)
        router.replaceScreen(Screen.Poets())
        mainController = MainController(storeFactory, instanceKeeper(), DefaultDispatchers)
        mainController.setNavigationItemSelectedListener { itemId ->
            if (itemId == lastSelectedMenuItemId) return@setNavigationItemSelectedListener
            when (itemId) {
                R.id.nav_poets -> {
                    router.replaceScreen(Screen.Poets())
                }
                R.id.nav_chosen -> {
                    router.replaceScreen(Screen.Favorites())
                }
            }
            lastSelectedMenuItemId = itemId
        }
        val mainView = MainView(view)
        mainController.onViewCreated(mainView, essentyLifecycle())
    }

    override fun onResume() {
        super.onResume()
        navigator?.let { navigatorHolder.setNavigator(it) }
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
