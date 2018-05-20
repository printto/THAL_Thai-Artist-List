package com.printto.printmov.thal.artist

import com.printto.printmov.thal.presenter.ArtistView
import java.util.ArrayList

class MockArtistRepo : ArtistRepo() {

    val artistList = ArrayList<Artist>()

    override fun loadAllArtists() {
        artistList.clear()
//        artistList.add(Artist(1,"PP Charin", "","","Facebook: Blue Neko Charin","Charin"))
        setChanged()
        notifyObservers()
    }

    override fun getArtists(): ArrayList<Artist> {
        return artistList
    }

}