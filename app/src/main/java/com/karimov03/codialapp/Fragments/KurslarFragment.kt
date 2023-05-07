package com.karimov03.codialapp.Fragments

import android.os.Bundle
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.karimov03.codialapp.Class.Kurslar
import com.karimov03.codialapp.DataBase.DbHelper
import com.karimov03.codialapp.R
import com.karimov03.codialapp.RvAdapter.RvKurslarAdapter
import com.karimov03.codialapp.RvAdapter.rvAction
import com.karimov03.codialapp.databinding.FragmentKurslarBinding
import com.karimov03.codialapp.databinding.ItemDialogKurslarAddBinding
import com.karimov03.codialapp.databinding.ItemRvBinding


class KurslarFragment : Fragment() ,rvAction{
    private val binding by lazy { FragmentKurslarBinding.inflate(layoutInflater) }
    private lateinit var dbHelper: DbHelper
    private lateinit var adapter: RvKurslarAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle=arguments
        val int = bundle?.getInt("int")
        if (int!=1){
            binding.btnAdd.visibility=View.INVISIBLE
            binding.actionName.text="Barcha kurslar"
        }
        dbHelper= DbHelper(requireContext())


//        btn_add bosilishi boshlanishi
        val alertDialog = AlertDialog.Builder(requireContext())
        val dialog = alertDialog.create()
        val dialogView = ItemDialogKurslarAddBinding.inflate(layoutInflater)
        dialog.setView(dialogView.root)
        dialogView.apply {
            btnYopish.setOnClickListener {
                dialog.dismiss()
            }

            btnQoshish.setOnClickListener {
                if (edtName.text.toString() != "" && edtHaqida.text.toString() != "") {
                    val kurslar = Kurslar(
                        edtName.text.toString(),
                        edtHaqida.text.toString()
                    )
                    dbHelper.AddKurs(kurslar)
                    Toast.makeText(context, "Saqlandi", Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                    onResume()

                }
                else Toast.makeText(context, "Ma'lumotlar to'liq emas", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnAdd.setOnClickListener {
            dialog.show()
        }

//        btn add bosilish kodi tugashi








        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val list=ArrayList<Kurslar>()
        dbHelper.getAllKurs().forEach {
            list.add(it)
        }
        adapter=RvKurslarAdapter(list)
        binding.rv.adapter=adapter
    }

    override fun OnClick(list: List<Kurslar>, position: Int) {
        val itemRvBinding=ItemRvBinding.inflate(layoutInflater)
        itemRvBinding.btnGo.setOnClickListener {
            val bundle=Bundle()
            bundle.putString("kurs_name",itemRvBinding.tvName.text.toString())
            findNavController().navigate(R.id.mentorFragment,bundle)
        }
    }
}