package com.maulana.kitabisa.movieslisttest.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.maulana.kitabisa.movieslisttest.R
import com.maulana.kitabisa.movieslisttest.config.AppConfig
import com.maulana.kitabisa.movieslisttest.config.AppConfig.API_KEY
import com.maulana.kitabisa.movieslisttest.config.AppDatabase
import com.maulana.kitabisa.movieslisttest.model.FavoritTable
import com.maulana.kitabisa.movieslisttest.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail_movie.*

/**
 * @author Maulana Rahmatullah
 * @describe class activity for detail movie page
 */
class DetailMovieActivity : AppCompatActivity() {
    private var movieId = 0
    private lateinit var detailViewModel : DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        init()
        getDetail()
        getReviews()
    }

    private fun init(){
        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        movieId = intent.extras!!.getInt("movieId")
        detailViewModel.findFavorite(movieId).observe(this, Observer {
            if(it!=null){
                img_btn_fav.visibility = VISIBLE
                img_btn_unfav.visibility = GONE
            }
        })

        rv_review.layoutManager = LinearLayoutManager(this)
        rv_review.setHasFixedSize(true)
    }

    private fun getDetail(){
        detailViewModel.getDetailMovie(movieId, API_KEY).observe(this, Observer {
            if(it!=null) {
                val result = it
                supportActionBar!!.title = it.title
                Glide.with(this).load(AppConfig.BASE_URL_IMAGE + it.posterPath).into(img_poster)
                tv_title.text = it.title
                tv_release_date.text = it.releaseDate
                tv_summary.text = it.overview

                img_btn_unfav.setOnClickListener {
                    detailViewModel.saveFavorit(
                        FavoritTable(
                            result.id,
                            result.title,
                            result.releaseDate,
                            result.overview,
                            result.posterPath
                        )
                    ).observe(this, Observer {
                        img_btn_fav.visibility = VISIBLE
                        img_btn_unfav.visibility = GONE
                    })
                }

                img_btn_fav.setOnClickListener {
                    detailViewModel.deleteFavorit(result.id).observe(this, Observer {
                        img_btn_fav.visibility = GONE
                        img_btn_unfav.visibility = VISIBLE
                    })
                }
            }else{
                onBackPressed()
            }
        })
    }

    private fun getReviews(){
        showProgressBar()
        detailViewModel.getListReviews(movieId, API_KEY).observe(this, Observer {
            hideProgressBar()
            if(it!=null) {
                if (it.size == 0)
                    tv_label_review.text = getString(R.string.label_tidak_ada_review)
                else
                    rv_review.adapter = ReviewsAdapter(this, it)
            }
        })
    }

    private fun showProgressBar(){
        pbar_review.visibility = VISIBLE
        rv_review.visibility = GONE
    }

    private fun hideProgressBar(){
        pbar_review.visibility = GONE
        rv_review.visibility = VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
            onBackPressed()

        return super.onOptionsItemSelected(item)
    }
}