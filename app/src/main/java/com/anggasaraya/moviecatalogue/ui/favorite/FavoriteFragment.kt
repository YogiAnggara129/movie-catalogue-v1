package com.anggasaraya.moviecatalogue.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.anggasaraya.moviecatalogue.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private lateinit var fragmentFavoriteBinding: FragmentFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            val sectionsPagerAdapter = SectionsPagerAdapter(requireContext(), childFragmentManager)
            fragmentFavoriteBinding.viewPager.adapter = sectionsPagerAdapter
            fragmentFavoriteBinding.tabs.setupWithViewPager(fragmentFavoriteBinding.viewPager)

            (activity as AppCompatActivity).supportActionBar?.elevation = 0f
        }
    }
}