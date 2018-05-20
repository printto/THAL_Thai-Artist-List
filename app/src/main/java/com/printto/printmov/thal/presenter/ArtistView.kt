package com.printto.printmov.thal.presenter

import com.printto.printmov.thal.artist.Artist

interface ArtistView {

    fun setArtistList(artists: ArrayList<Artist>)

}