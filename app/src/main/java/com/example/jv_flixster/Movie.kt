
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import org.json.JSONArray
import kotlinx.parcelize.Parcelize


@Parcelize
data class Movie (
    val movieId: Int,
    private val posterPath: String,
    val title: String,
    val overview: String,

)  :Parcelable {
    @IgnoredOnParcel
    val posterImageUrl: String ="https://image.tmdb.org/t/p/w342/$posterPath"

    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJsonArray.length()) {
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview")
                    )
                )
            }
            return movies
        }
    }
}