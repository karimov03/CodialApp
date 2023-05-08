package com.karimov03.codialapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import com.karimov03.codialapp.R
import com.karimov03.codialapp.databinding.FragmentGuruhlarBinding
import com.karimov03.codialapp.databinding.ItemTabBinding

class GuruhlarFragment : Fragment() {
    private  val binding by lazy { FragmentGuruhlarBinding.inflate(layoutInflater) }
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var list: ArrayList<String>
    private lateinit var kursi:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        kursi= arguments?.getString("kurs_name").toString()
        binding.actionName.text=kursi


        list=ArrayList()
        list.add("Ochilgan guruhlar")
        list.add("Ochilayotgan guruhlar")
        viewPagerAdapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        binding.viewpager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewpager)
        val tabCount = binding.tabLayout.tabCount
        for (i in 0 until tabCount) {
            val tabItem = ItemTabBinding.inflate(layoutInflater)
            val tab = binding.tabLayout.getTabAt(i)
            tab?.customView = tabItem.root
            tabItem.tvName.text = list[i]
        }

        return binding.root
    }
}