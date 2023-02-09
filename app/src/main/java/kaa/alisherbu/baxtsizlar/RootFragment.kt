package kaa.alisherbu.baxtsizlar

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.arkivanov.mvikotlin.core.store.StoreFactory

class RootFragment(
    private val storeFactory: StoreFactory,
    private val poetDispatchers: PoetDispatchers,
) : Fragment(R.layout.fragment_root) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            childFragmentManager
                .beginTransaction()
                .add(R.id.root_container, poetsFragment())
                .commit()
        }
    }

    private fun poetsFragment(): PoetsFragment =
        PoetsFragment(
            storeFactory = storeFactory,
            dispatchers = poetDispatchers,
        )
}
