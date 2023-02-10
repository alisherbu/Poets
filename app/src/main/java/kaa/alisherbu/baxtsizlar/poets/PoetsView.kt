package kaa.alisherbu.baxtsizlar.poets

import android.view.View
import com.arkivanov.mvikotlin.core.utils.diff
import com.arkivanov.mvikotlin.core.view.BaseMviView
import com.arkivanov.mvikotlin.core.view.ViewRenderer
import kaa.alisherbu.baxtsizlar.databinding.FragmentPoetsBinding


class PoetsView(root: View) : BaseMviView<List<Poet>, Event>() {
    private val binding = FragmentPoetsBinding.bind(root)
    private val poetsAdapter = PoetsAdapter()

    init {
        binding.rvPoets.adapter = poetsAdapter
        poetsAdapter.setOnItemClickListener {
            dispatch(Event.ItemClicked(it))
        }
    }

    override val renderer: ViewRenderer<List<Poet>>
        get() = diff {
            diff(get = List<Poet>::toList, compare = { a, b -> a === b }) {
                poetsAdapter.models = it
            }
        }
}
