package com.anggasaraya.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.databinding.FragmentTVShowBinding
import com.anggasaraya.moviecatalogue.viewmodel.ViewModelFactory
import com.anggasaraya.moviecatalogue.vo.Resource
import com.anggasaraya.moviecatalogue.vo.Status

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
            val tvShowObserver = Observer<Resource<PagedList<TVShowEntity>>> { tvShows ->
                if(tvShows != null){
                    when(tvShows.status){
                        Status.LOADING -> fragmentTVShowBinding.progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTVShowBinding.progressBar.visibility = View.GONE
                            tvShowAdapter.submitList(tvShows.data!!)
                            tvShowAdapter.notifyDataSetChanged()
                        }
                        Status.ERROR -> {
                            fragmentTVShowBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            viewModel.getAllTVShows().observe(viewLifecycleOwner, tvShowObserver)

            with(fragmentTVShowBinding.rvTvshow){
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
    }
}