package kaa.alisherbu.baxtsizlar.main

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.arkivanov.essenty.lifecycle.essentyLifecycle
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import kaa.alisherbu.baxtsizlar.R
import kaa.alisherbu.baxtsizlar.Screen
import kaa.alisherbu.baxtsizlar.navigation.BackButtonListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named


class MainFragment : Fragment(R.layout.fragment_main), BackButtonListener {
    private val navigator: Navigator by lazy {
        AppNavigator(requireActivity(), R.id.mainContainer, childFragmentManager)
    }
    private val navigatorHolder: NavigatorHolder by inject(named("main"))
    private val viewModel: MainViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (childFragmentManager.findFragmentById(R.id.mainContainer) == null) {
            navigator.applyCommands(arrayOf(Replace(Screen.Poets())))
        }
        val mainView = MainView(view)
        viewModel.onViewCreated(mainView, essentyLifecycle())
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.mainContainer)
//        Log.d("NavLog", "onBackPressed, MainFragment, ${fragment?.javaClass?.name}")
//        if ((fragment as? BackButtonListener)?.onBackPressed() == false) {
//            viewModel.onBackPressed()
//            return true
//        }
        val k = (fragment as? BackButtonListener)?.onBackPressed() == true
        Log.d("NavLog", "onBackPressed, RootFragment, ${fragment?.javaClass?.name}")
        return k
    }
}
