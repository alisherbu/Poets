package kaa.alisherbu.baxtsizlar.root

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import kaa.alisherbu.baxtsizlar.navigation.LocalCiceroneHolder
import kaa.alisherbu.baxtsizlar.navigation.RouterProvider

class RootViewModel(
    private val ciceroneHolder: LocalCiceroneHolder
):ViewModel(), RouterProvider {
    private val cicerone get() = ciceroneHolder.getCicerone("root")
    override val router: Router
        get() = cicerone.router

    fun onBackPressed(){
        router.exit()
    }
}
