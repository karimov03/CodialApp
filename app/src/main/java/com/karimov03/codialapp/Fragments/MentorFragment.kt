package com.karimov03.codialapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.karimov03.codialapp.Class.Kurslar
import com.karimov03.codialapp.Class.Mentor
import com.karimov03.codialapp.DataBase.DbHelper
import com.karimov03.codialapp.RvAdapter.RvMentorAdapter
import com.karimov03.codialapp.RvAdapter.rvActionMentor
import com.karimov03.codialapp.databinding.FragmentMentorBinding
import com.karimov03.codialapp.databinding.ItemDialogKurslarAddBinding
import com.karimov03.codialapp.databinding.ItemDialogMentorAddBinding

class MentorFragment : Fragment() {
    private val binding by lazy { FragmentMentorBinding.inflate(layoutInflater) }
    private lateinit var dbHelper: DbHelper
    private lateinit var adapter: RvMentorAdapter
    private lateinit var kursi:String
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dbHelper=DbHelper(requireContext())

        kursi= arguments?.getString("kurs_name").toString()
        binding.actionName.text=kursi
        Toast.makeText(context, "${kursi}", Toast.LENGTH_SHORT).show()

        Resume()
        
        binding.btnAdd.setOnClickListener {


            val alertDialog = AlertDialog.Builder(requireContext())
            val dialog = alertDialog.create()
            val dialogView = ItemDialogMentorAddBinding.inflate(layoutInflater)
            dialogView.layoutAdd.visibility=View.VISIBLE
            dialogView.layoutEdit.visibility=View.GONE
            dialog.setView(dialogView.root)
            dialog.show()
            dialogView.apply {
                btnQoshish.setOnClickListener {
                    if (dialogView.edtName.text.toString()!="" && dialogView.edtSecondName.text.toString()!="" &&dialogView.edtFatherName.text.toString()!="" ){
                        val mentor=Mentor(
                            dialogView.edtName.text.toString()+" "+dialogView.edtSecondName.text.toString(),
                            dialogView.edtFatherName.text.toString(),
                            kursi
                        )
                        dbHelper.addMentor(mentor)
                        Toast.makeText(context, "Saqlandi", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                        Resume()
                    }
                    else Toast.makeText(context, "Ma'lumotlar to'liq emas", Toast.LENGTH_SHORT).show()

                }
            }

        }
        return binding.root
    }
    fun Resume() {
        val alertDialog = AlertDialog.Builder(requireContext())
        val dialog = alertDialog.create()
        val dialogView = ItemDialogMentorAddBinding.inflate(layoutInflater)
        dialogView.layoutEdit.visibility=View.VISIBLE
        dialogView.layoutAdd.visibility=View.GONE
        dialog.setView(dialogView.root)


        val list=ArrayList<Mentor>()
        dbHelper.getAllMentor(kursi).forEach {
            list.add(it)
        }
        adapter=RvMentorAdapter(list,object:rvActionMentor{
            override fun EditClick(list: List<Mentor>, position: Int) {
                dialog.show()
                dialogView.btnOzgartirish.setOnClickListener {
                    val mentor=Mentor(
                        dialogView.edtName.text.toString()+dialogView.edtSecondName.text.toString(),
                        dialogView.edtFatherName.text.toString(),
                        list[position].kursi
                    )
                    dbHelper.editMentor(list[position].id!!.toInt(),mentor)
                    Resume()
                    dialog.dismiss()
                }
                dialogView.btnYopish.setOnClickListener {
                    dialog.dismiss()
                }

            }

            override fun DeleteClick(list: List<Mentor>, position: Int) {
                dbHelper.deleteMentor(list[position].id!!.toInt())
                Resume()
            }
        })
        binding.rv.adapter=adapter
    }
}