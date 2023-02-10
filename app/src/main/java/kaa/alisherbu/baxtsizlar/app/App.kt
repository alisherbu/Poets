package kaa.alisherbu.baxtsizlar.app

import android.app.Application
import kaa.alisherbu.baxtsizlar.di.Navigation
import kaa.alisherbu.baxtsizlar.di.Poets
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(Poets.module)
            modules(Navigation.module)
        }
    }
}
