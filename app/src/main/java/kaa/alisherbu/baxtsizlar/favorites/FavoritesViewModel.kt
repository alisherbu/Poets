package kaa.alisherbu.baxtsizlar.favorites

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import kaa.alisherbu.baxtsizlar.navigation.LocalCiceroneHolder
import kaa.alisherbu.baxtsizlar.navigation.RouterProvider

class FavoritesViewModel(
    private val ciceroneHolder: LocalCiceroneHolder,
) : ViewModel(), RouterProvider {
    private val cicerone get() = ciceroneHolder.getCicerone("main")
    override val router: Router get() = cicerone.router

    fun onBackPressed() {
        router.exit()
    }
}
