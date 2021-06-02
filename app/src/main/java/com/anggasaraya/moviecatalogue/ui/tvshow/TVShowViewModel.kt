package com.anggasaraya.moviecatalogue.ui.tvshow

import androidx.lifecycle.ViewModel
import com.anggasaraya.moviecatalogue.data.TVShowEntity
import com.anggasaraya.moviecatalogue.helper.DataDummy

class TVShowViewModel : ViewModel() {
    private lateinit var id: String
    
    fun setSelectedTVShow(id: String){
        this.id = id
    }

    fun getSelectedTVShow(): TVShowEntity {
        lateinit var tvShow: TVShowEntity
        val tvShowEntities = DataDummy.generateDummyTVShows()
        for (tvShowEntity in tvShowEntities) {
            if (tvShowEntity.id == id) {
                tvShow = tvShowEntity
            }
        }
        return tvShow
    }
    
    fun getAllTVShows() : ArrayList<TVShowEntity> = DataDummy.generateDummyTVShows()
}