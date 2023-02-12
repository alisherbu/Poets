package kaa.alisherbu.baxtsizlar.bio

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import kaa.alisherbu.baxtsizlar.navigation.LocalCiceroneHolder
import kaa.alisherbu.baxtsizlar.navigation.RouterProvider

class BiographyViewModel(
    private val ciceroneHolder: LocalCiceroneHolder,
) : ViewModel(), RouterProvider {
    private val cicerone get() = ciceroneHolder.getCicerone("outside")
    override val router: Router get() = cicerone.router

    fun onBackPressed() {
        router.exit()
    }
}
