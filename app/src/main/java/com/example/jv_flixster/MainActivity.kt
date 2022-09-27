package com.example.jv_flixster
import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixster.R
import okhttp3.Headers

//
//private const val com.example.jv_flixster.NOW_PLAYING_URL="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
//private const val com.example.jv_flixster.TAG="com.example.jv_flixster.MainActivity"
//


private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

const val NOW_PLAYING_URL= "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"

//private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"


class MainActivity : AppCompatActivity(){
    private val movies = mutableListOf<Movie>()
    private lateinit var rvMovies: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvMovies = findViewById(R.id.rvMovies)

        val movieAdapter = MovieAdapter(this, movies)
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(this)

        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api-key"] = API_KEY

        client.get(NOW_PLAYING_URL, object : JsonHttpResponseHandler(){
            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
                Log.e(ContentValues.TAG, "onFailure $statusCode")
            }

            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
                Log.i(ContentValues.TAG, "onSuccess: JSON data $json")
                val movieJsonArray = json.jsonObject.getJSONArray("results")
                movies.addAll(Movie.fromJsonArray(movieJsonArray))
                movieAdapter.notifyDataSetChanged() //update movies
                Log.i(ContentValues.TAG, "Movie list $movies")
            }

        })
    }
}







//
//
//class com.example.jv_flixster.MainActivity : AppCompatActivity() {
//
//    private val movies= mutableListOf<com.example.jv_flixster.Movie>()
//    private lateinit var rvMovies: RecyclerView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        setContentView(R.layout.activity_main)
//        rvMovies=findViewById(R.id.rvMovies)
//
//        val movieAdapter=com.example.jv_flixster.MovieAdapter(this, movies)
//        rvMovies.adapter=movieAdapter
//        rvMovies.layoutManager=LinearLayoutManager(this)
//
//        val client = AsyncHttpClient()
//        client.get(com.example.jv_flixster.NOW_PLAYING_URL, object : JsonHttpResponseHandler(){
//
//            override fun onFailure(
//                statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?
//            ) {
//                Log.e(com.example.jv_flixster.TAG, "ERROR")
//            }
//
//            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
//                Log.i(com.example.jv_flixster.TAG, "onSuccess: JSON data $json")
//                try {
//                    val movieJsonArray = json.jsonObject.getJSONArray("results")
//                    movies.addAll(com.example.jv_flixster.Movie.fromJsonArray(movieJsonArray))
//                    movieAdapter.notifyDataSetChanged()
//                    Log.i(com.example.jv_flixster.TAG, "com.example.jv_flixster.Movie list $movies")
//                } catch (e: JSONException){
//                    Log.e(com.example.jv_flixster.TAG, "Encountered exception: $e")
//                }
//            }
//
//        })
//    }
//}