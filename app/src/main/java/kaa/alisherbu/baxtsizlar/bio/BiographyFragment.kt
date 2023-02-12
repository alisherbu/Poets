package kaa.alisherbu.baxtsizlar.bio

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import kaa.alisherbu.baxtsizlar.R
import kaa.alisherbu.baxtsizlar.navigation.BackButtonListener
import org.koin.android.ext.android.inject


class BiographyFragment : Fragment(R.layout.fragment_biography), BackButtonListener {
    private val viewModel: BiographyViewModel by inject()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("NavLog", "onViewCreated, BiographyFragment")
    }
    override fun onBackPressed(): Boolean {
        Log.d("NavLog", "onBackPressed, BiographyFragment")
        viewModel.onBackPressed()
        return true
    }

}
