package com.printto.printmov.thal.artist

class Artist (val id: Int,
              val name: String = "",
              val artistImageURL: String = "",
              val artImageURL: String = "",
              val description: String = "",
              val contract: String = "",
              val artType: String = "") {

    override fun toString(): String {
        return name
    }

}