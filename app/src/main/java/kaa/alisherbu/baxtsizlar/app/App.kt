package kaa.alisherbu.baxtsizlar.app

import android.app.Application
import kaa.alisherbu.baxtsizlar.di.Navigation
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(Navigation.module)
        }
    }
}
