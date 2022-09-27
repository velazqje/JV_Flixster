package com.example.jv_flixster
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixster.R

//






private const val TAG = "MovieAdapter"

class MovieAdapter(private val context: MainActivity, private val movies: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    @SuppressLint("LongLogTag")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_movie_list, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("LongLogTag")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position $position")
        val currentMovies = movies[position]
        holder.bind(currentMovies)
    }

    override fun getItemCount() = movies.size

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvDescription = itemView.findViewById<TextView>(R.id.tvDescription)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie: Movie){
            tvTitle.text = movie.titleID
            tvDescription.text = movie.descriptionID
            Glide.with(context)
                .load(movie.posterImageURL)
                .centerCrop()
                .into(ivPoster)
        }

        override fun onClick(p0: View?) {
            val movie = movies[adapterPosition]
            Toast.makeText(context, movie.titleID, Toast.LENGTH_SHORT).show()

        }
    }
}