package kaa.alisherbu.baxtsizlar.root

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Command
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

class RootFragment : Fragment(R.layout.fragment_root), BackButtonListener {

    private val navigator: Navigator by lazy {
        AppNavigator(requireActivity(), R.id.root_container, childFragmentManager)
    }
    private val navigatorHolder: NavigatorHolder by inject(named("root"))
    private val viewModel: RootViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (childFragmentManager.findFragmentById(R.id.root_container) == null) {
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

    override fun onBackPressed(): Boolean {
        val fragment = childFragmentManager.findFragmentById(R.id.root_container)
//        if ((fragment as? BackButtonListener)?.onBackPressed() == false) {
//            viewModel.onBackPressed()
//            return false
//        }
        val k = (fragment as? BackButtonListener)?.onBackPressed() == true
        Log.d("NavLog", "onBackPressed, RootFragment, ${fragment?.javaClass?.name}")
        return k
    }
}
