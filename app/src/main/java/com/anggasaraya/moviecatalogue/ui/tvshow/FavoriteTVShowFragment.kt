package com.anggasaraya.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.databinding.FragmentFavoriteTVShowBinding
import com.anggasaraya.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteTVShowFragment : Fragment() {
    private lateinit var fragmentFavoriteTVShowBinding: FragmentFavoriteTVShowBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentFavoriteTVShowBinding = FragmentFavoriteTVShowBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteTVShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TVShowViewModel::class.java]
            val tvShowAdapter = TVShowAdapter()

            fragmentFavoriteTVShowBinding.progressBar.visibility = View.VISIBLE
            val tvShowsObserver = Observer<PagedList<TVShowEntity>> { tvShows ->
                fragmentFavoriteTVShowBinding.progressBar.visibility = View.GONE
                tvShowAdapter.submitList(tvShows)
                tvShowAdapter.notifyDataSetChanged()
            }
            viewModel.getAllFavoriteTVShows().observe(viewLifecycleOwner, tvShowsObserver)

            with(fragmentFavoriteTVShowBinding.rvMovie){
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}