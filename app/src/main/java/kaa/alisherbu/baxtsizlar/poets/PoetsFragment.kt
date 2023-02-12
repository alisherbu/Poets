package kaa.alisherbu.baxtsizlar.poets

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arkivanov.essenty.lifecycle.essentyLifecycle
import kaa.alisherbu.baxtsizlar.R
import kaa.alisherbu.baxtsizlar.navigation.BackButtonListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class PoetsFragment : Fragment(R.layout.fragment_poets), BackButtonListener {
    private val viewModel: PoetsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val poetsListView = PoetsView(view)
        viewModel.onViewCreated(poetsListView, viewLifecycleOwner.essentyLifecycle())
    }

    override fun onBackPressed(): Boolean {
        viewModel.onBackPressed()
        return true
    }
}
