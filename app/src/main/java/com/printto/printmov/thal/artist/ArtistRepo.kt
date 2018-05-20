package com.printto.printmov.thal.artist

import java.util.*

abstract class ArtistRepo : Observable(){

    abstract fun loadAllArtists()
    abstract fun getArtists(): ArrayList<Artist>

}