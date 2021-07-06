package com.anggasaraya.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.anggasaraya.moviecatalogue.viewmodel.ViewModelFactory

class FavoriteMovieFragment : Fragment() {
    private lateinit var fragmentFavoriteMovieBinding: FragmentFavoriteMovieBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentFavoriteMovieBinding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return fragmentFavoriteMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null){
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]
            val movieAdapter = MovieAdapter()

            fragmentFavoriteMovieBinding.progressBar.visibility = View.VISIBLE
            val moviesObserver = Observer<PagedList<MovieEntity>> { movies ->
                fragmentFavoriteMovieBinding.progressBar.visibility = View.GONE
                movieAdapter.submitList(movies)
                movieAdapter.notifyDataSetChanged()
            }
            viewModel.getAllFavoriteMovies().observe(viewLifecycleOwner, moviesObserver)

            with(fragmentFavoriteMovieBinding.rvMovieFav){
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }
}