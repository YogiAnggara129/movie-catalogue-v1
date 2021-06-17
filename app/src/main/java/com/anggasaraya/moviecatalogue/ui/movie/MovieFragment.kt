package com.anggasaraya.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.databinding.FragmentMovieBinding
import com.anggasaraya.moviecatalogue.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {
    private lateinit var fragmentMovieAdapter: FragmentMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentMovieAdapter = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieAdapter.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()
            val moviesObserver = Observer<List<MovieEntity>> { movies ->
                movieAdapter.setMovies(movies)
                //movieAdapter.notifyDataSetChanged()
            }
            viewModel.getAllMovies().observe(this, moviesObserver)
            /*movieAdapter.setMovies(movies)*/

            with(fragmentMovieAdapter.rvMovie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}