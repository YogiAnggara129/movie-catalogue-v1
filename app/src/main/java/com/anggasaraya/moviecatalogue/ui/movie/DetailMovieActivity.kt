package com.anggasaraya.moviecatalogue.ui.movie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.anggasaraya.moviecatalogue.databinding.ActivityDetailMovieBinding
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

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]

        val extras = intent.extras
        if(extras != null){
            val id = extras.getString(EXTRA_MOVIE)
            if(id != null){
                viewModel.setSelectedMovie(id)
                val movie = viewModel.getSelectedMovie()
                with(detailMovieBinding){
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
                            .load(movie.imagePath)
                            .into(imgPoster)
                }
            }
        }
    }
}