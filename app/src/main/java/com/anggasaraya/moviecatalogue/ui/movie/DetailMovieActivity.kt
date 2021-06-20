package com.anggasaraya.moviecatalogue.ui.movie

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.anggasaraya.moviecatalogue.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var detailMovieBinding: ActivityDetailMovieBinding
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailMovieBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        val extras = intent.extras
        if(extras != null){
            val id = extras.getString(EXTRA_MOVIE)
            if(id != null){
                val movieObserver = Observer<MovieEntity> { movie ->
                    Log.i(TAG, "datanyaa: $movie")
                    detailMovieBinding.progressBar.visibility = View.GONE
                    with(detailMovieBinding) {
                        tvItemTitle.text = movie.title
                        tvSubtext.text = StringBuilder()
                                .append(movie.dateReleased)
                                .append(" | ")
                                .append(movie.genre)
                        tvScore.text = movie.userScore
                        tvStatus.text = movie.status
                        tvLanguage.text = movie.language
                        tvItemDescription.text = movie.description
                        tvItemBudget.text = movie.budget
                        tvItemIncome.text = movie.income
                        Glide.with(this@DetailMovieActivity)
                                .load("https://www.themoviedb.org/t/p/w300_and_h450_bestv2/${movie.imagePath}")
                                .into(imgPoster)
                    }
                }
                viewModel.getSelectedMovie(id).observe(this, movieObserver)
            }
        }
    }
}