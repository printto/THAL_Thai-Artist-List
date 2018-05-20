package com.printto.printmov.thal

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import com.printto.printmov.thal.presenter.ArtistPresenter
import com.printto.printmov.thal.presenter.ArtistView
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.AdapterView
import com.printto.printmov.thal.artist.*
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity(), ArtistView {

    var presenter = ArtistPresenter(this, OnlineArtistRepo())
    var adapter: ArtistItemAdapter? = null
    var artists = ArrayList<Artist>();

    override fun setArtistList(artists: ArrayList<Artist>) {
        this.artists.clear()
        this.artists.addAll(artists)

        adapter?.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
    }

    private fun initComponents() {
        adapter = ArtistItemAdapter(this, artists)
        itemList.adapter = adapter
        presenter.start()
        search_box.addTextChangedListener(watcher(presenter))

        var mainActivity = this
        itemList.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(arg0: AdapterView<*>, arg1: View, position: Int, arg3: Long) {
                val o : Artist = itemList.getItemAtPosition(position) as Artist
                var intent: Intent = Intent(mainActivity, ArtistActivity::class.java)
                intent.putExtra("title",o.name)
                intent.putExtra("art_image",o.artImageURL)
                intent.putExtra("artist_image",o.artistImageURL)
                intent.putExtra("category",o.artType)
                intent.putExtra("description",o.description)
                intent.putExtra("contract",o.contract)
                startActivity(intent)
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    class watcher(val presenter: ArtistPresenter) : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            presenter.search(p0.toString())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.title.equals("All")){
            search_box.setText("")
        }
        else{
            search_box.setText(item.title)
        }
        return true
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed(){
        if(!search_box.text.toString().equals("")){
            search_box.setText("")
        }
        else{
            super.onBackPressed()
        }
    }

}
