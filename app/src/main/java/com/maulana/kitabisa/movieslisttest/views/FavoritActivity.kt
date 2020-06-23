package com.maulana.kitabisa.movieslisttest.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.maulana.kitabisa.movieslisttest.R
import com.maulana.kitabisa.movieslisttest.model.Results
import com.maulana.kitabisa.movieslisttest.viewmodel.FavoritViewModel
import com.maulana.kitabisa.movieslisttest.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_favorit.*

/**
 * @author Maulana Rahmatullah
 * @describe class activity for list favorit page
 */
class FavoritActivity : AppCompatActivity() {
    private lateinit var favoritViewModel: FavoritViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorit)
        supportActionBar!!.title = getString(R.string.label_favorit)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        init()
    }

    override fun onResume() {
        super.onResume()
        showDataFavorit()
    }

    private fun init(){
        favoritViewModel = ViewModelProviders.of(this).get(FavoritViewModel::class.java)

        rv_favorit.layoutManager = LinearLayoutManager(this)
        rv_favorit.setHasFixedSize(true)
    }

    private fun showDataFavorit(){
        favoritViewModel.getAllFavorit().observe(this, Observer {
            val listMovies = mutableListOf<Results>()
            for (item in it) {
                listMovies.add(Results(item.id, item.title, item.releaseDate, item.overview, item.posterPath))
            }
            rv_favorit.adapter = MoviesAdapter(this, listMovies)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }
}