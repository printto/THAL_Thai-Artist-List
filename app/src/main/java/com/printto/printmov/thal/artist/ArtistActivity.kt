package com.printto.printmov.thal.artist

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.printto.printmov.thal.R
import kotlinx.android.synthetic.main.activity_artist.*
import java.io.InputStream
import java.net.URL
import kotlin.concurrent.thread

class ArtistActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)

        initComponents()
    }

    private fun initComponents() {
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        artist_name.text = intent.getStringExtra("title")
        art_type.text = intent.getStringExtra("category")
        description.text = intent.getStringExtra("description")
        var buttonStrings = intent.getStringExtra("contract").split(' ')
        contract.setText(buttonStrings[0])
        var loadDrawable = ArtistItemAdapter.LoadDrawable()
        val drawable = loadDrawable.getDrawable(intent.getStringExtra("art_image"))
        art_image.setImageDrawable(drawable)
        loadDrawable = ArtistItemAdapter.LoadDrawable()
        val drawable2 = loadDrawable.getDrawable(intent.getStringExtra("artist_image"))
        artist_image.setImageDrawable(drawable2)
    }

    fun onContractClicked(view : View){
        var buttonStrings = intent.getStringExtra("contract").split(' ')
        var intent = Intent (Intent.ACTION_VIEW, Uri.parse(buttonStrings[1]))
        startActivity(intent)
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

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        onBackPressed()
        return true
    }

}
