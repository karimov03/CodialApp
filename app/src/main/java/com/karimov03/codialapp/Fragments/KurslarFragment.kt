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
import com.karimov03.codialapp.databinding.FragmentKurslarBinding
import com.karimov03.codialapp.databinding.ItemDialogKurslarAddBinding
import com.karimov03.codialapp.databinding.ItemRvBinding


class KurslarFragment : Fragment() {
    private val binding by lazy { FragmentKurslarBinding.inflate(layoutInflater) }
    private lateinit var dbHelper: DbHelper
    private lateinit var adapter: RvKurslarAdapter
    private var int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        int = bundle?.getInt("int")!!.toInt()
        if (int != 1) {
            binding.btnAdd.visibility = View.INVISIBLE
            binding.actionName.text = "Barcha kurslar"
        }
        dbHelper = DbHelper(requireContext())
        ///////////////////
        nitifayAdapter()
        /////////////////

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
                    nitifayAdapter()
                    dialog.dismiss()

                } else Toast.makeText(context, "Ma'lumotlar to'liq emas", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnAdd.setOnClickListener {
            dialog.show()
        }

//        btn add bosilish kodi tugashi

        binding.btnBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        return binding.root
    }

    fun nitifayAdapter() {
        val list = ArrayList<Kurslar>()
        dbHelper.getAllKurs().forEach {
            list.add(it)
        }
        adapter = RvKurslarAdapter(list, object : RvKurslarAdapter.RvAction {
            override fun OnClick(list: List<Kurslar>, position: Int) {
                if (int==3) {
                    val bundle = Bundle()
                    bundle.putString("kurs_name", list[position].name)
                    findNavController().navigate(R.id.mentorFragment, bundle)
                }
                if (int==2) {
                    val bundle = Bundle()
                    bundle.putString("kurs_name", list[position].name)
                    findNavController().navigate(R.id.guruhlarFragment, bundle)
                }








            }
        })
        binding.rv.adapter = adapter
    }
//
}