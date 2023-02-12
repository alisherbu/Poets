package kaa.alisherbu.baxtsizlar.di

import kaa.alisherbu.baxtsizlar.navigation.LocalCiceroneHolder
import org.koin.core.qualifier.named
import org.koin.dsl.module

object Navigation {
    val module = module {
        single { LocalCiceroneHolder() }

        single(named("outside")) {
            get<LocalCiceroneHolder>().getCicerone("outside").getNavigatorHolder()
        }

        single(named("root")) {
            get<LocalCiceroneHolder>().getCicerone("root").getNavigatorHolder()
        }
        single(named("main")) {
            get<LocalCiceroneHolder>().getCicerone("main").getNavigatorHolder()
        }
    }
}
