package kaa.alisherbu.baxtsizlar.poets

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.arkivanov.essenty.instancekeeper.instanceKeeper
import com.arkivanov.essenty.lifecycle.essentyLifecycle
import com.arkivanov.mvikotlin.core.store.StoreFactory
import kaa.alisherbu.baxtsizlar.R

class PoetsFragment(
    private val storeFactory: StoreFactory,
    private val dispatchers: PoetDispatchers,
) : Fragment(R.layout.fragment_poets) {
    private lateinit var controller: PoetsController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        controller = PoetsController(
            storeFactory,
            essentyLifecycle(),
            instanceKeeper(),
            dispatchers
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val poetsListView = PoetsListView(view)
        controller.onViewCreated(poetsListView, viewLifecycleOwner.essentyLifecycle())
    }
}
