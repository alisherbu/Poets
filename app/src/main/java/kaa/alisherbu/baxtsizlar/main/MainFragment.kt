package kaa.alisherbu.baxtsizlar.main

import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import kaa.alisherbu.baxtsizlar.R
import kaa.alisherbu.baxtsizlar.databinding.FragmentMainBinding
import kaa.alisherbu.baxtsizlar.poets.PoetsFragment
import kaa.alisherbu.baxtsizlar.replaceFragment
import org.koin.android.ext.android.inject


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private lateinit var navigator: Navigator
    private val navigatorHolder: NavigatorHolder by inject()
    private val router: Router by inject()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.apply {
            navigator =
                AppNavigator(requireActivity(), appBarMain.mainContainer.id, childFragmentManager)
            router.replaceScreen(FragmentScreen { poetsFragment() })
            navView.setCheckedItem(R.id.nav_poets)
            appBarMain.toolbar.setNavigationOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            navView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_poets -> {
                        router.replaceScreen(FragmentScreen { poetsFragment() })
                    }
                }
                true
            }
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

    private fun poetsFragment(): PoetsFragment =
        PoetsFragment()
}
