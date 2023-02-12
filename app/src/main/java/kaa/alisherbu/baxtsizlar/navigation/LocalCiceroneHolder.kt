package kaa.alisherbu.baxtsizlar.navigation

import android.util.Log
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router


class LocalCiceroneHolder {
    private val containers = HashMap<String, Cicerone<Router>>()

    fun getCicerone(containerTag: String) = containers.getOrPut(containerTag) {
        Log.d("NavLog", "containers=${containers.map { it.key }}")
        Cicerone.create()
    }
}

interface RouterProvider {
    val router: Router
}
