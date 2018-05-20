package com.printto.printmov.thal.artist

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.app.Activity
import android.view.LayoutInflater
import android.widget.ImageView

import android.widget.TextView
import com.printto.printmov.thal.R
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.AsyncTask
import org.json.JSONArray
import java.io.File
import java.io.InputStream
import java.net.URL
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.io.Console
import java.net.HttpURLConnection
import kotlin.concurrent.thread


class ArtistItemAdapter(var context: Context, var rowItems: ArrayList<Artist>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var holder: ViewHolder? = null
        var convertView = convertView
        val mInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        convertView = mInflater.inflate(R.layout.artist_item, parent, false)
        holder = ViewHolder()
        holder.art_image = convertView.findViewById(R.id.art_image) as ImageView
        holder.artist_name = convertView.findViewById(R.id.artist_name) as TextView
        holder.art_category = convertView.findViewById(R.id.art_category) as TextView
        holder.artist_image = convertView.findViewById(R.id.artist_image) as ImageView
        val row_pos = rowItems.get(position)
        var loadDrawable = LoadDrawable()
        val drawable = loadDrawable.getDrawable(row_pos.artImageURL)
        holder.art_image!!.setImageDrawable(drawable)
        loadDrawable = LoadDrawable()
        val drawable2 = loadDrawable.getDrawable(row_pos.artistImageURL)
        holder.artist_image!!.setImageDrawable(drawable2)
        holder.artist_name!!.setText(row_pos.name)
        holder.art_category!!.setText(row_pos.artType)
        convertView.tag = holder
        return convertView
    }

    override fun getItem(position: Int): Any {
        return rowItems.get(position)
    }

    override fun getItemId(position: Int): Long {
        return rowItems.indexOf(getItem(position)).toLong();
    }

    override fun getCount(): Int {
        return rowItems.size
    }

    private inner class ViewHolder {
        internal var art_image: ImageView? = null
        internal var artist_name: TextView? = null
        internal var art_category: TextView? = null
        internal var artist_image: ImageView? = null
    }

    private inner class Drawer {
        lateinit var drawable: Drawable
        lateinit var drawable2: Drawable
    }

    class LoadDrawable {

            lateinit var drawable: Drawable

            fun getDrawable(url: String): Drawable {
                thread {
                    var myUrl = URL(url)
                    var inputStream = myUrl.getContent() as InputStream
                    drawable = Drawable.createFromStream(inputStream, null)
                }
                while (!this::drawable.isInitialized) {

                }
                return drawable
            }

    }

}