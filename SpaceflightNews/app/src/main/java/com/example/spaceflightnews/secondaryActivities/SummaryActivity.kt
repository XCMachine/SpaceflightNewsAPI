package com.example.spaceflightnews.secondaryActivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import coil.load
import com.example.spaceflightnews.databinding.ActivitySummeryBinding

class SummaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySummeryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySummeryBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)

        //Using get intent, we could get the keyword from 'getStringExtra' URL from the main activity.
        val summaryText = intent.getStringExtra("summary")
        val imageUrlText = intent.getStringExtra("imageUrl")
        val titleText = intent.getStringExtra("title")

        binding.articleTitleText.text = titleText
        binding.summeryText.text = summaryText
        binding.imageUrl.load(imageUrlText)
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