package com.example.spaceflightnews.secondaryActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import coil.load
import com.example.spaceflightnews.R

class SummeryActivity : AppCompatActivity() {
    private lateinit var titleTextView: TextView
    private lateinit var summeryTextView: TextView
    private lateinit var imageUrlView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summery)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        //Using get intent, we could get the keyword from 'getStringExtra' URL from the main activity.
        val intent = intent
        val summaryText = intent.getStringExtra("summary")
        val imageUrlText = intent.getStringExtra("imageUrl")
        val titleText = intent.getStringExtra("title")

        titleTextView = findViewById(R.id.articleTitleText)
        titleTextView.text = titleText

        summeryTextView = findViewById(R.id.summeryText)
        summeryTextView.text = summaryText

        imageUrlView = findViewById(R.id.imageUrl)
        imageUrlView.load("$imageUrlText")
    }

    /**
     * It allows us to use the actionbar's back button, to use it as home ID, then we put a behavior. In our case we want to use finnish() to destroy the current activity and
     * go back to the previous activity.
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}