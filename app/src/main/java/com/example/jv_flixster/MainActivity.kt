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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers

//
//private const val com.example.jv_flixster.NOW_PLAYING_URL="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
//private const val com.example.jv_flixster.TAG="com.example.jv_flixster.MainActivity"
//


private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

const val NOW_PLAYING_URL= "https://api.themoviedb.org/3/movie/now_playing"

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
        params["api_key"] = API_KEY

        client[NOW_PLAYING_URL, params, object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                // Access a JSON array response with `json.jsonArray`


                val gson = Gson()
                val arr_type = object : TypeToken<List><Movie>() {}.type

                val models : List<Movie> = gson.fromJson()

                val movieJsonArray = json.jsonObject.getJSONArray("results")
                movies.addAll(Movie.fromJsonArray(movieJsonArray))
                movieAdapter.notifyDataSetChanged() //update movies


                Log.d("DEBUG ARRAY", json.jsonObject.getJSONArray("results").toString())
                // Access a JSON object response with `json.jsonObject`
                Log.d("DEBUG OBJECT", json.jsonObject.getJSONArray("results").toString())
            }


            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String,
                throwable: Throwable?
            ) {
                Log.e("ERROR", response)

            }
         }]}}
//
//        client.get(NOW_PLAYING_URL, object : JsonHttpResponseHandler(){
//            override fun onFailure(statusCode: Int, headers: Headers?, response: String?, throwable: Throwable?) {
//                Log.e("MainActivity", "onFailure $statusCode")
//            }
//
//            override fun onSuccess(statusCode: Int, headers: Headers?, json: JSON) {
//                Log.i("MainActivity", "onSuccess: JSON data $json")
//                val movieJsonArray = json.jsonObject.getJSONArray("results")
//                movies.addAll(Movie.fromJsonArray(movieJsonArray))
//                movieAdapter.notifyDataSetChanged() //update movies
//                Log.i("MainActivity", "Movie list $movies")
//            }

//        })
//    }
//}
