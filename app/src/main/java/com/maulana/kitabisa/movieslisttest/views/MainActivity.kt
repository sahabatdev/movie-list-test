package com.maulana.kitabisa.movieslisttest.views

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.maulana.kitabisa.movieslisttest.R
import com.maulana.kitabisa.movieslisttest.config.AppConfig
import com.maulana.kitabisa.movieslisttest.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_bottom_sheet.view.*

/**
 * @author Maulana Rahmatullah
 * @describe class activity for main page
 */
class MainActivity : AppCompatActivity() {
    private var category: String = "popular"
    private lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        showData()
    }

    private fun init() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        rv_movies.layoutManager = LinearLayoutManager(this)
        rv_movies.setHasFixedSize(true)

        btn_category.setOnClickListener {
            showListBottomSheet()
        }
    }

    private fun showListBottomSheet() {
        val sheetView = layoutInflater.inflate(R.layout.item_bottom_sheet, null)
        val bottomSheet = BottomSheetDialog(this)
        bottomSheet.setContentView(sheetView)
        bottomSheet.show()
        sheetView.btn_popular.setOnClickListener {
            bottomSheet.dismiss()
            category = "popular"
            showData()
        }
        sheetView.btn_upcoming.setOnClickListener {
            bottomSheet.dismiss()
            category = "upcoming"
            showData()
        }
        sheetView.btn_top_rated.setOnClickListener {
            bottomSheet.dismiss()
            category = "top_rated"
            showData()
        }
        sheetView.btn_now_playing.setOnClickListener {
            bottomSheet.dismiss()
            category = "now_playing"
            showData()
        }
    }

    private fun showData() {
        showProgressBar()
        mainViewModel.getMovies(category, AppConfig.API_KEY).observe(this, Observer {
            hideProgressBar()
            if(it!=null)
                rv_movies.adapter = MoviesAdapter(this, it)
        })
    }

    fun showProgressBar(){
        pbar_movies.visibility = VISIBLE
        rv_movies.visibility = GONE
    }

    fun hideProgressBar(){
        pbar_movies.visibility = GONE
        rv_movies.visibility = VISIBLE

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_toolbar, menu!!)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(R.id.favorit == item.itemId){
            val intent = Intent(this, FavoritActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}