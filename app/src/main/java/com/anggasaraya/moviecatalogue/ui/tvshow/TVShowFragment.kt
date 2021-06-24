package com.anggasaraya.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.databinding.FragmentTVShowBinding
import com.anggasaraya.moviecatalogue.viewmodel.ViewModelFactory

class TVShowFragment : Fragment() {
    private lateinit var fragmentTVShowBinding: FragmentTVShowBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentTVShowBinding = FragmentTVShowBinding.inflate(layoutInflater, container, false)
        return fragmentTVShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TVShowViewModel::class.java]
            val tvShowAdapter = TVShowAdapter()

            fragmentTVShowBinding.progressBar.visibility = View.VISIBLE
            val moviesObserver = Observer<List<TVShowEntity>> { tvShoww ->
                fragmentTVShowBinding.progressBar.visibility = View.GONE
                tvShowAdapter.setTVShows(tvShoww)
                tvShowAdapter.notifyDataSetChanged()
            }
            viewModel.getAllTVShows().observe(viewLifecycleOwner, moviesObserver)

            with(fragmentTVShowBinding.rvTvshow){
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}