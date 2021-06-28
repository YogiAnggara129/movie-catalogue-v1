package com.anggasaraya.moviecatalogue.ui.movie

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anggasaraya.moviecatalogue.R
import com.anggasaraya.moviecatalogue.data.local.entity.MovieEntity
import com.anggasaraya.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.anggasaraya.moviecatalogue.viewmodel.ViewModelFactory
import com.anggasaraya.moviecatalogue.vo.Resource
import com.anggasaraya.moviecatalogue.vo.Status
import com.bumptech.glide.Glide

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var detailMovieBinding: ActivityDetailMovieBinding
    private lateinit var viewModel: MovieViewModel

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(detailMovieBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MovieViewModel::class.java]

        val extras = intent.extras
        if(extras != null){
            val id = extras.getString(EXTRA_MOVIE)
            if(id != null){
                viewModel.setSelectedId(id)
                viewModel.movieData.observe(this, layoutObserver())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.top_menu, menu)
        if (viewModel.isFavorite().value!!) menu.getItem(0).setIcon(R.drawable.ic_favorite_white_fill)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_favorite -> {
                if (viewModel.isFavorite().value!!) {
                    viewModel.setFavorite()
                    Toast.makeText(this, "Dihapus dari favorit", Toast.LENGTH_SHORT).show()
                    item.setIcon(R.drawable.ic_favorite_white_blank)
                } else {
                    viewModel.setFavorite()
                    Toast.makeText(this, "Dimasukan ke favorit", Toast.LENGTH_SHORT).show()
                    item.setIcon(R.drawable.ic_favorite_white_fill)
                }
                true
            }
            else -> true
        }
    }

    private fun layoutObserver(): Observer<Resource<MovieEntity>> {
        return Observer { movie ->
            if (movie != null){
                when(movie.status){
                    Status.LOADING -> detailMovieBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        detailMovieBinding.progressBar.visibility = View.GONE
                        with(detailMovieBinding) {
                            tvItemTitle.text = movie.data?.title
                            tvSubtext.text = StringBuilder()
                                    .append(movie.data?.dateReleased)
                                    .append(" | ")
                                    .append(movie.data?.genre)
                            tvScore.text = movie.data?.userScore
                            tvStatus.text = movie.data?.status
                            tvLanguage.text = movie.data?.language
                            tvItemDescription.text = movie.data?.description
                            tvItemBudget.text = movie.data?.budget
                            tvItemIncome.text = movie.data?.income
                            Glide.with(this@DetailMovieActivity)
                                    .load("https://www.themoviedb.org/t/p/w300_and_h450_bestv2/${movie.data?.imagePath}")
                                    .into(imgPoster)
                        }
                    }
                    Status.ERROR -> {
                        detailMovieBinding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}