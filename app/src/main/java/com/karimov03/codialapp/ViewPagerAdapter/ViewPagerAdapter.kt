package com.karimov03.codialapp.Fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter (fm:FragmentManager):FragmentPagerAdapter(fm){
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            0->ChildGuruhlarFragment()
            1->ChildGuruhlarFragment()
            else->ChildGuruhlarFragment()
        }


    }

}