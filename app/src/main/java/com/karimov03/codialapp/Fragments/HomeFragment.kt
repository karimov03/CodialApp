package com.karimov03.codialapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.karimov03.codialapp.R
import com.karimov03.codialapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.btnKurslar.setOnClickListener {
            val bundle=Bundle()
            bundle.putInt("int",1)

            findNavController().navigate(R.id.kurslarFragment,bundle)
        }
        binding.btnGuruhlar.setOnClickListener {
            val bundle=Bundle()
            bundle.putInt("int",2)
            findNavController().navigate(R.id.kurslarFragment,bundle)
        }
        binding.btnMentorlar.setOnClickListener {
            val bundle=Bundle()
            bundle.putInt("int",3)
            findNavController().navigate(R.id.kurslarFragment,bundle)
        }
        return binding.root
    }


}