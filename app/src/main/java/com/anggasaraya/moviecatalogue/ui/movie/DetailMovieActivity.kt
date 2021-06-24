package com.anggasaraya.moviecatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anggasaraya.moviecatalogue.R
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.anggasaraya.moviecatalogue.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var detailMovieBinding: ActivityDetailMovieBinding
    private var isFavorite = false

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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_favorite -> {
                if (isFavorite) {
                    isFavorite = false
                    Toast.makeText(this, "Dihapus dari favorit", Toast.LENGTH_SHORT).show()
                    item.setIcon(R.drawable.ic_favorite_white_blank)
                }
                else {
                    isFavorite = true
                    Toast.makeText(this, "Dimasukan ke favorit", Toast.LENGTH_SHORT).show()
                    item.setIcon(R.drawable.ic_favorite_white_fill)
                }
                return true
            }
            else -> return true
        }
    }
}