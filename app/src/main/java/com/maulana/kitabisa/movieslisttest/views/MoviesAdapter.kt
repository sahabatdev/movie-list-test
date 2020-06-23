package com.maulana.kitabisa.movieslisttest.views

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maulana.kitabisa.movieslisttest.R
import com.maulana.kitabisa.movieslisttest.config.AppConfig
import com.maulana.kitabisa.movieslisttest.model.Results

/**
 * @author Maulana Rahmatullah
 * @describe class adapter for item list movie
 */
class MoviesAdapter(private val activity: AppCompatActivity, private val movieList: MutableList<Results>) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPoster: ImageView = itemView.findViewById(R.id.img_poster)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvReleaseDate: TextView = itemView.findViewById(R.id.tv_release_date)
        val tvSummary: TextView = itemView.findViewById(R.id.tv_summary)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(LayoutInflater.from(activity.applicationContext).inflate(R.layout.item_list_movie, parent, false))
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = movieList[position]

        Glide.with(activity.applicationContext).load(AppConfig.BASE_URL_IMAGE+item.posterPath).into(holder.imgPoster)
        holder.tvTitle.text = item.title
        holder.tvReleaseDate.text = item.releaseDate
        if(item.overview.length > 100)
            holder.tvSummary.text = item.overview.substring(0,100)
        else
            holder.tvSummary.text = item.overview

        holder.itemView.setOnClickListener {
            val intent = Intent(activity, DetailMovieActivity::class.java)
            intent.putExtra("movieId",item.id)
            activity.startActivity(intent)
        }
    }

}
