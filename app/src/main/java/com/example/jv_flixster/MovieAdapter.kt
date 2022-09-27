
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jv_flixster.R

const val MOVIE_EXTRA="MOVIE_EXTRA"
private const val TAG="MovieAdapter"
class MovieAdapter(private val context: MainActivity, private val movies: MutableList<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val view=LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position $position")
        val movie=movies[position]
        holder.bind(movie)
    }

    override fun getItemCount()= movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        private val ivPoster=itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle=itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvDescription=itemView.findViewById<TextView>(R.id.tvDescription)
        init{
            itemView.setOnClickListener(this)
        }
        fun bind(movie: Movie){
            tvTitle.text=movie.title
            tvDescription.text=movie.overview
            Glide.with(context).load(movie.posterImageUrl).into(ivPoster)
        }

        override fun onClick(v: View?) {
            val movie =movies[adapterPosition]
            val intent= Intent(context, MovieFragment::class.java)
            intent.putExtra(MOVIE_EXTRA, movie)
            context.startActivity(intent)
        }
    }
}