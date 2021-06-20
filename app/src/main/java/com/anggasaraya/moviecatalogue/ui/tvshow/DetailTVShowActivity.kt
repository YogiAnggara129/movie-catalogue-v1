package com.anggasaraya.moviecatalogue.ui.tvshow

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.anggasaraya.moviecatalogue.data.local.entity.TVShowEntity
import com.anggasaraya.moviecatalogue.databinding.ActivityDetailTVShowBinding
import com.anggasaraya.moviecatalogue.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

class DetailTVShowActivity : AppCompatActivity() {
    private lateinit var detailTVShowBinding: ActivityDetailTVShowBinding

    companion object {
        const val EXTRA_TVSHOW = "extra_tvshow"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailTVShowBinding = ActivityDetailTVShowBinding.inflate(layoutInflater)
        setContentView(detailTVShowBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[TVShowViewModel::class.java]

        val extras = intent.extras
        if(extras != null){
            val id = extras.getString(EXTRA_TVSHOW)
            if(id != null){
                detailTVShowBinding.progressBar.visibility = View.VISIBLE
                val tvShowObserver = Observer<TVShowEntity> { tvShow ->
                    detailTVShowBinding.progressBar.visibility = View.GONE
                    with(detailTVShowBinding) {
                        tvItemTitle.text = tvShow.title
                        tvSubtext.text = StringBuilder()
                                .append(tvShow.dateReleased)
                                .append(" | ")
                                .append(tvShow.genre)
                        tvScore.text = tvShow.userScore
                        tvStatus.text = tvShow.status
                        tvLanguage.text = tvShow.language
                        tvItemDescription.text = tvShow.description
                        Glide.with(this@DetailTVShowActivity)
                                .load("https://www.themoviedb.org/t/p/w300_and_h450_bestv2/${tvShow.imagePath}")
                                .into(imgPoster)
                    }
                }

                viewModel.getSelectedTVShow(id).observe(this, tvShowObserver)
            }
        }
    }
}