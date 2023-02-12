package kaa.alisherbu.baxtsizlar.main

import android.view.View
import androidx.core.view.GravityCompat
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import kaa.alisherbu.baxtsizlar.R
import kaa.alisherbu.baxtsizlar.databinding.FragmentMainBinding

class MainView(root: View) : BaseMviView<State, Intent>() {
    private val binding = FragmentMainBinding.bind(root)

    init {
        binding.apply {
            navView.setCheckedItem(R.id.nav_poets)
            appBarMain.toolbar.setNavigationOnClickListener {
                dispatch(Intent.NavigationIconClicked)
            }
            navView.setNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.nav_poets -> dispatch(Intent.MenuItemClicked(0))
                    R.id.nav_chosen -> dispatch(Intent.MenuItemClicked(1))
                    R.id.nav_info -> dispatch(Intent.MenuItemClicked(2))
                }
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
        }
    }

    override val renderer: ViewRenderer<State>
        get() = diff {
//            diff()
        }

    fun openDrawer() {
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }
}
