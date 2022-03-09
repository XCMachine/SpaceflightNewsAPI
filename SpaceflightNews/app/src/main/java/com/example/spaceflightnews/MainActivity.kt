package com.example.spaceflightnews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflightnews.model.Article
import com.example.spaceflightnews.secondaryActivities.SummeryActivity
import com.example.spaceflightnews.spaceflightNewsAPI.ArticlesAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), RecyclerAdapter.OnItemClickListener {
    //Getting the RecyclerView and the Adapter ready
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerAdapter
    private var exampleList: List<Article>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Connecting to it's ID from the XML
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.hasFixedSize()

        passJSONData()
    }

    /**
     * Using a seperate method to get the JSON data using RetroFit dependency, using RetroFit will pass up the base url of the source of the website's JSON data
     * and going through a GsonConverterFactory to translate and build it. After that we are creating the API interface class and then calling the list of item's class
     * in order to use enqueue, as we use Callback interface for response sequence.
     */
    private fun passJSONData() {
        val baseUrl = "https://api.spaceflightnewsapi.net/v3/"

        //RetroFit
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Creating the SpaceFlight interface and then assigning the same list to get the list of articles within the interface SpaceflightAPI
        val jsonSpaceApi = retrofit.create(ArticlesAPI::class.java)
        val call: Call<List<Article>> = jsonSpaceApi.getArticles()

        //Instead of using execute, we use enqueue to implement an interface Callback
        call.enqueue(object : Callback<List<Article>> {
            override fun onResponse(call: Call<List<Article>>, response: Response<List<Article>>) {
                //We get the List of Article class as we assign a new variable to connect is as a response.
                exampleList = response.body()
                //We are connecting the RecyclerView to it's LayoutManager, using LinearLayoutManager and connecting to it's context.
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                //Then we make the RecyclerAdapter class for the RecyclerView, it's parameters is the List of Article class and it's context.
                adapter = RecyclerAdapter(exampleList!!, this@MainActivity)
                recyclerView.adapter = adapter
                adapter.setOnItemClickListener(this@MainActivity)
            }

            override fun onFailure(call: Call<List<Article>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    /**
     * As we implement our MainActivity class to it's new made interface from the RecyclerAdapter,
     * we implement it's function for onItemClick with parameter 'position' type Int
     */
    override fun onItemClick(position: Int) {
        val summaryIntent = Intent(this, SummeryActivity::class.java)
        val clickedItem = exampleList!![position]

        //This collection of URL's are for the summary activity to have extra strings as keywords
        val extraSummaryURL = "summary"
        val extraImageURL = "imageUrl"
        val extraTitleURL = "title"

        summaryIntent.putExtra(extraTitleURL, clickedItem.title)
        summaryIntent.putExtra(extraSummaryURL, clickedItem.summary)
        summaryIntent.putExtra(extraImageURL, clickedItem.imageUrl)

        startActivity(summaryIntent)
    }
}