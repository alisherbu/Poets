package kaa.alisherbu.baxtsizlar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PoetsAdapter : RecyclerView.Adapter<PoetsAdapter.PoetListViewHolder>() {
    inner class PoetListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun populateModel(poet: Poet) {
            itemView.findViewById<TextView>(R.id.poetName).text = poet.name
            itemView.setOnClickListener {
                onClick.invoke(poet.id)
            }
        }
    }

    var models = listOf<Poet>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var onClick: (id: Int) -> Unit = { _ ->
    }

    fun setOnItemClickListener(onClick: (id: Int) -> Unit) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoetListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_poet, parent, false)
        return PoetListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PoetListViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

    override fun getItemCount() = models.size

}
