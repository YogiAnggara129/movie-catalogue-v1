package com.anggasaraya.moviecatalogue.ui.tvshow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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
                viewModel.setSelectedTVShow(id)
                val tvShow = viewModel.getSelectedTVShow()
                with(detailTVShowBinding){
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
                            .load(tvShow.imagePath)
                            .into(imgPoster)
                }
            }
        }
    }
}