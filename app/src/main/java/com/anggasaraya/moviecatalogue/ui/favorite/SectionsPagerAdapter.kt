package com.anggasaraya.moviecatalogue.ui.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.anggasaraya.moviecatalogue.R
import com.anggasaraya.moviecatalogue.ui.movie.FavoriteMovieFragment
import com.anggasaraya.moviecatalogue.ui.movie.MovieFragment
import com.anggasaraya.moviecatalogue.ui.tvshow.FavoriteTVShowFragment
import com.anggasaraya.moviecatalogue.ui.tvshow.TVShowFragment

class SectionsPagerAdapter (private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.movie, R.string.tv_show)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTVShowFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? = mContext.resources.getString(TAB_TITLES[position])

    override fun getCount(): Int = TAB_TITLES.size

}