package com.karimov03.codialapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karimov03.codialapp.R
import com.karimov03.codialapp.databinding.FragmentChildGuruhlarBinding

class ChildGuruhlarFragment : Fragment() {
    private val binding by lazy { FragmentChildGuruhlarBinding.inflate(layoutInflater) }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return binding.root
    }

}