package com.printto.printmov.thal.artist

import android.os.AsyncTask
import org.json.JSONArray
import java.net.URL
import java.util.ArrayList

class OnlineArtistRepo : ArtistRepo() {

    val artistList = ArrayList<Artist>()

    override fun loadAllArtists() {

        object : AsyncTask<String, Void, String>(){

            override fun doInBackground(vararg params: String?): String {
                var json = URL("http://www.printmov.com/THAL/artist.json").readText()
                return json
            }

            override fun onPostExecute(result: String) {
                val jsonObj = JSONArray(result.substring(result.indexOf("["), result.lastIndexOf("]") + 1))
                for (i in 0..jsonObj!!.length() - 1) {
                    val currentJson = jsonObj.getJSONObject(i)
                    val artist = Artist(currentJson.getInt("id")
                            , currentJson.getString("name")
                            , currentJson.getString("artistImageURL")
                            , currentJson.getString("artImageURL")
                            , currentJson.getString("description")
                            , currentJson.getString("contract")
                            , currentJson.getString("artType"))
                    getArtists().add(artist)
                }
                setChanged()
                notifyObservers()
            }

        }.execute()

    }

    override fun getArtists(): ArrayList<Artist> {
        return artistList
    }

}