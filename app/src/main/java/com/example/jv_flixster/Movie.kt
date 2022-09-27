package com.example.jv_flixster
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.json.JSONArray


@Parcelize
data class Movie (
    val movieID: Int,
    private val posterID: String,
    val titleID: String,
    val descriptionID: String,

)  :Parcelable {
    @IgnoredOnParcel
    val posterImageURL: String ="https://image.tmdb.org/t/p/w342/$posterID"

    companion object {
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies = mutableListOf<Movie>()
            for (i in 0 until movieJsonArray.length()) {
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("movieID"),
                        movieJson.getString("posterID"),
                        movieJson.getString("titleID"),
                        movieJson.getString("descriptionID")
                    )
                )
            }
            return movies
        }
    }
}