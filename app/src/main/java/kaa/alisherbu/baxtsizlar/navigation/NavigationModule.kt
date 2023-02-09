package kaa.alisherbu.baxtsizlar.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Cicerone.Companion.create
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router

object NavigationModule {

    private val cicerone: Cicerone<Router> = create()

    fun router(): Router = cicerone.router

    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()
}
