package kaa.alisherbu.baxtsizlar.main

import android.view.View
import androidx.core.view.GravityCompat
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import kaa.alisherbu.baxtsizlar.R
import kaa.alisherbu.baxtsizlar.databinding.FragmentMainBinding

class MainView(root: View) : BaseMviView<Model, Event>() {
    private val binding = FragmentMainBinding.bind(root)

    init {
        binding.apply {
            navView.setCheckedItem(R.id.nav_poets)
            appBarMain.toolbar.setNavigationOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }
            navView.setNavigationItemSelectedListener { menuItem ->
                dispatch(Event.MenuItemClicked(menuItem.itemId))
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
        }
    }
}
