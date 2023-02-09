package kaa.alisherbu.baxtsizlar.main

import android.os.Bundle
import android.view.View
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.arkivanov.mvikotlin.core.store.StoreFactory
import kaa.alisherbu.baxtsizlar.R
import kaa.alisherbu.baxtsizlar.databinding.FragmentMainBinding
import kaa.alisherbu.baxtsizlar.poets.PoetDispatchers
import kaa.alisherbu.baxtsizlar.poets.PoetsFragment
import kaa.alisherbu.baxtsizlar.replaceFragment


class MainFragment(
    private val storeFactory: StoreFactory,
    private val dispatchers: PoetDispatchers,
) : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.apply {
            replaceFragment(poetsFragment(), appBarMain.mainContainer.id)
            navView.setCheckedItem(R.id.nav_poets)
            appBarMain.toolbar.setNavigationOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            navView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_poets -> {
                        replaceFragment(poetsFragment(), appBarMain.mainContainer.id)
                    }
                }
                true
            }
        }
    }

    private fun poetsFragment(): PoetsFragment =
        PoetsFragment(
            storeFactory = storeFactory,
            dispatchers = dispatchers,
        )
}
