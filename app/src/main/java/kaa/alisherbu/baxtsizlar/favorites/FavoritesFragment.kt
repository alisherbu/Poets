package kaa.alisherbu.baxtsizlar.favorites

import androidx.fragment.app.Fragment
import kaa.alisherbu.baxtsizlar.R
import kaa.alisherbu.baxtsizlar.navigation.BackButtonListener
import org.koin.android.ext.android.inject


class FavoritesFragment : Fragment(R.layout.fragment_favorites), BackButtonListener {
    private val viewModel:FavoritesViewModel by inject()
    override fun onBackPressed(): Boolean {
        viewModel.onBackPressed()
        return true
    }

}
