package kaa.alisherbu.baxtsizlar

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kaa.alisherbu.baxtsizlar.main.MainFragment
import kaa.alisherbu.baxtsizlar.navigation.NavigationModule

class RootFragment : Fragment(R.layout.fragment_root) {

    private lateinit var navigator: Navigator
    private val navigatorHolder = NavigationModule.navigatorHolder()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = AppNavigator(requireActivity(), R.id.root_container, childFragmentManager)
        if (savedInstanceState == null) {
            navigator.applyCommands(arrayOf<Command>(Replace(Screen.Main())))
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun mainFragment(): MainFragment = MainFragment(
    )

    private fun mainScreen() = FragmentScreen {
        mainFragment()
    }
}
