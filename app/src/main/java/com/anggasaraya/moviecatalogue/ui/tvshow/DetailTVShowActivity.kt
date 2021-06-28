package com.anggasaraya.moviecatalogue.ui.tvshow

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anggasaraya.moviecatalogue.R
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.databinding.ActivityDetailTVShowBinding
import com.anggasaraya.moviecatalogue.viewmodel.ViewModelFactory
import com.anggasaraya.moviecatalogue.vo.Resource
import com.anggasaraya.moviecatalogue.vo.Status
import com.bumptech.glide.Glide

class DetailTVShowActivity : AppCompatActivity() {
    private lateinit var detailTVShowBinding: ActivityDetailTVShowBinding
    private lateinit var viewModel: TVShowViewModel

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailTVShowBinding = ActivityDetailTVShowBinding.inflate(layoutInflater)
        setContentView(detailTVShowBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[TVShowViewModel::class.java]

        val extras = intent.extras
        if(extras != null){
            val id = extras.getString(EXTRA_TVSHOW)
            if(id != null){
                viewModel.setSelectedId(id)
                viewModel.tvShowData.observe(this, layoutObserver())
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

    private fun layoutObserver(): Observer<Resource<TVShowEntity>> {
        return Observer { tvShow ->
            if (tvShow != null){
                when(tvShow.status){
                    Status.LOADING -> detailTVShowBinding.progressBar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        detailTVShowBinding.progressBar.visibility = View.GONE
                        with(detailTVShowBinding) {
                            tvItemTitle.text = tvShow.data?.title
                            tvSubtext.text = StringBuilder()
                                    .append(tvShow.data?.dateReleased)
                                    .append(" | ")
                                    .append(tvShow.data?.genre)
                            tvScore.text = tvShow.data?.userScore
                            tvStatus.text = tvShow.data?.status
                            tvLanguage.text = tvShow.data?.language
                            tvItemDescription.text = tvShow.data?.description
                            Glide.with(this@DetailTVShowActivity)
                                    .load("https://www.themoviedb.org/t/p/w300_and_h450_bestv2/${tvShow.data?.imagePath}")
                                    .into(imgPoster)
                        }
                    }
                    Status.ERROR -> {
                        detailTVShowBinding.progressBar.visibility = View.GONE
                        Toast.makeText(this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}