package com.example.spaceflightnews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spaceflightnews.get.Article

class RecyclerAdapter(listItems: List<Article>, var context: Context) : RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {
    //We are assigning the exampleList ArrayList<Articles> and connecting it with the class 'listItem' parameter
    private var exampleList: ArrayList<Article> = listItems as ArrayList<Article>

    //Using the listener for the interface later on
    private var mListener: OnItemClickListener? = null

    /**
     * This interface stands for the Summary to get every single summary on each individual item and passes JSON data within them.
     * It includes the onItemClick function and it's parameter to pass it on 'position' type Int
     */
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: MainActivity) {
        mListener = listener
    }

    /** Connecting the context then making a LayoutInflater along with it's context of the activity, then selecting the card_items.xml, then using the ViewGroup, making sure
     * to put attachToRoot into false
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.card_items, parent, false)
        return RecyclerViewHolder(v)
    }

    /**
     * We connect the ViewHolder class that is in the ViewGroup and then using the those texts to get the JSON data and using listItems original position of that list
     */
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val listItem = exampleList[position]

        holder.titleText.text = listItem.title

        val output = String.format("Published: ${listItem.publishedAt}")
        holder.publishedAtText.text = output
    }

    /**
     * Getting the item count of the max size of the list of items
     */
    override fun getItemCount(): Int = exampleList.size

    /**
     * We use 'inner' keyword in order to have access and use the new interface a.k.a. 'mListener' and initializing the TextViews to it's ID here!
     */
    inner class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var titleText: TextView = itemView.findViewById(R.id.titleText)
        var publishedAtText: TextView = itemView.findViewById(R.id.publishedAtText)

        init {
            //Setting the new interface listener here, using it's position parameter
            itemView.setOnClickListener {
                if (mListener != null) {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        mListener!!.onItemClick(position)
                    }
                }
            }
        }
    }

    //Making sure that the exampleList variable is connected to the listItems parameter within the RecyclerAdapter class
    init {
        exampleList = listItems as ArrayList<Article>
    }
}