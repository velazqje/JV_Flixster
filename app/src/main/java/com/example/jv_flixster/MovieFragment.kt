
////
//////
////
////
//
////
////import android.util.Log
////import android.view.LayoutInflater
////import android.view.View
////import android.view.ViewGroup
////import android.widget.Toast
////import androidx.core.widget.ContentLoadingProgressBar
////import androidx.fragment.app.Fragment
////import androidx.recyclerview.widget.GridLayoutManager
////import androidx.recyclerview.widget.RecyclerView
////import com.codepath.asynchttpclient.AsyncHttpClient
////import com.codepath.asynchttpclient.RequestParams
////import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
////import com.google.gson.Gson
////import com.google.gson.reflect.TypeToken
////import okhttp3.Headers
////import org.json.JSONObject
//
//// --------------------------------//
//// CHANGE THIS TO BE YOUR API KEY  //
//// --------------------------------//
//
////private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
//
//
//class MovieFragment: Fragment() {
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
//        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
//        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
//        val context = view.context
//        recyclerView.layoutManager = GridLayoutManager(context, 1) // spanCount to decide how many column will shows
//        updateAdapter(progressBar, recyclerView)
//        return view
//    }
//
//    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
//        progressBar.show()
//
//        // Create and set up an AsyncHTTPClient() here
//        val client = AsyncHttpClient()
//        val params = RequestParams()
//        params["api-key"] = API_KEY
//        // Using the client, perform the HTTP request
//        client[
//                "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed",
//                params,
//                object : JsonHttpResponseHandler()
//                {
//                    override fun onSuccess(
//                        statusCode: Int,
//                        headers: Headers,
//                        json: JsonHttpResponseHandler.JSON
//                    ) {
//                        // The wait for a response is over
//                        progressBar.hide()
//
//                        // Parse JSON into Models
//                        //val resultsJSON : JSONObject = json.jsonObject.get("results") as JSONObject
//                        //val booksRawJSON : String = resultsJSON.get("books").toString()
//                        val resultsJSON : String = json.jsonObject.get("results").toString()
//                        val gson = Gson()
//                        val arrayMovieType = object : TypeToken<List<Movie>>() {}.type
//
//                        val models : List<Movie> = gson.fromJson(resultsJSON, arrayMovieType) // Fix me!
//                        recyclerView.adapter = MovieAdapter(models, this@MovieFragment)
//                        // Look for this in Logcat:
//                        Log.d("MovieFragment", "response successful")
//                    }
//
//                    override fun onFailure(
//                        statusCode: Int,
//                        headers: Headers?,
//                        errorResponse: String,
//                        t: Throwable?
//                    ) {
//                        // The wait for a response is over
//                        progressBar.hide()
//                        // If the error is not null, log it!
//                        t?.message?.let {
//                            Log.e("MovieFragment", errorResponse)
//                        }
//                    }
//                }]
//    }
//
//
//    override fun onItemClick(item: Movie) {
//        Toast.makeText(context, "test: " + item.titleID, Toast.LENGTH_LONG).show()
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//