package com.karimov03.codialapp.RvAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karimov03.codialapp.Class.Kurslar
import com.karimov03.codialapp.Class.Mentor
import com.karimov03.codialapp.databinding.ItemRvBinding
import com.karimov03.codialapp.databinding.ItemRvMentorBinding

class RvMentorAdapter(val list:ArrayList<Mentor>):RecyclerView.Adapter<RvMentorAdapter.vh>() ,rvActionMentor{
    class vh(val itemrvmentor: ItemRvMentorBinding):RecyclerView.ViewHolder(itemrvmentor.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  vh{
        return vh(ItemRvMentorBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun getItemCount(): Int=list.size

    override fun onBindViewHolder(holder: vh, position: Int) {
        holder.itemrvmentor.tvName.text=list[position].name
    }

    override fun OnClick(list: List<Kurslar>, position: Int) {

    }
}
interface rvActionMentor{
    fun OnClick(list: List<Kurslar>,position: Int)
}