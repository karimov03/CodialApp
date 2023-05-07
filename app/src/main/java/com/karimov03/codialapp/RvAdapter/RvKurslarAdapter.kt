package com.karimov03.codialapp.RvAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karimov03.codialapp.Class.Kurslar
import com.karimov03.codialapp.databinding.ItemRvBinding

class RvKurslarAdapter(val list:ArrayList<Kurslar>):RecyclerView.Adapter<RvKurslarAdapter.vh>() ,rvAction{
    class vh(val itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  vh{
        return vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int=list.size

    override fun onBindViewHolder(holder: vh, position: Int) {
        holder.itemRvBinding.tvName.text=list[position].name
    }

    override fun OnClick(list: List<Kurslar>, position: Int) {

    }
}
interface rvAction{
    fun OnClick(list: List<Kurslar>,position: Int)
}