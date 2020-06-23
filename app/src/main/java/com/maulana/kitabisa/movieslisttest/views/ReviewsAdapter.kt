package com.maulana.kitabisa.movieslisttest.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maulana.kitabisa.movieslisttest.R
import com.maulana.kitabisa.movieslisttest.model.ResultReview

/**
 * @author Maulana Rahmatullah
 * @describe class adapter for item list reviews detail movie
 */
class ReviewsAdapter(val activity: DetailMovieActivity, val reviews: MutableList<ResultReview>) : RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {
    class ReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAuthor: TextView = itemView.findViewById(R.id.tv_author)
        val tvContent: TextView = itemView.findViewById(R.id.tv_content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(LayoutInflater.from(activity.applicationContext).inflate(R.layout.item_list_review, parent, false))
    }

    override fun getItemCount(): Int = reviews.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val item = reviews[position]

        holder.tvAuthor.text = item.author
        holder.tvContent.text = item.content
    }

}
