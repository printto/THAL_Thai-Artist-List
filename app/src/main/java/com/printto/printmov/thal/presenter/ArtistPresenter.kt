package com.printto.printmov.thal.presenter

import com.printto.printmov.thal.artist.*
import java.util.*

class ArtistPresenter(val view: ArtistView, vararg val repositories: ArtistRepo) : Observer {

    var artists = ArrayList<Artist>()

    override fun update(obj: Observable?, arg: Any?) {
        if (repositories.contains(obj)) {
            artists.addAll((obj as ArtistRepo).getArtists())
        }
        updateArtistList(artists)
    }

    fun updateArtistList(artists: ArrayList<Artist>) {
        view.setArtistList(artists)
    }

    fun start() {
        for (repository in repositories) {
            repository.addObserver(this)
            repository.loadAllArtists()
        }
    }

    fun search(keyword: String) {
        if (keyword == null || keyword.trim().equals("")) {
            updateArtistList(artists)
            return
        }
        var temp = ArrayList<Artist>()
        for (artist in artists) {
            if (artist.name.toLowerCase().contains(keyword.toLowerCase())) {
                temp.add(artist)
            }
        }

        for (artist in artists) {
            if (artist.artType.toLowerCase().contains(keyword.toLowerCase())) {
                temp.add(artist)
            }
        }
        updateArtistList(temp)
    }

}