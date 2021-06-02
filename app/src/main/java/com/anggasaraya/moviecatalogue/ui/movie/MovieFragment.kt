package com.anggasaraya.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.anggasaraya.moviecatalogue.databinding.FragmentMovieBinding

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
            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val movies = viewModel.getAllMovies()
            val movieAdapter = MovieAdapter()
            movieAdapter.setMovies(movies)

            with(fragmentMovieAdapter.rvMovie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}