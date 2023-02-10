@file:Suppress("FunctionName")

package kaa.alisherbu.baxtsizlar

import com.github.terrakok.cicerone.androidx.FragmentScreen
import kaa.alisherbu.baxtsizlar.favorites.FavoritesFragment
import kaa.alisherbu.baxtsizlar.main.MainFragment
import kaa.alisherbu.baxtsizlar.poets.PoetsFragment

object Screen {
    fun Poets() = FragmentScreen {
        PoetsFragment()
    }

    fun Main() = FragmentScreen {
        MainFragment()
    }

    fun Root() = FragmentScreen {
        RootFragment()
    }

    fun Favorites() = FragmentScreen{
        FavoritesFragment()
    }
}
