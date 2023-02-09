package kaa.alisherbu.baxtsizlar.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Cicerone.Companion.create
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import org.koin.dsl.module

object Navigation {
    private val cicerone: Cicerone<Router> = create()
    val module = module {
        single { cicerone.router }
        single { cicerone.getNavigatorHolder() }
    }
}
