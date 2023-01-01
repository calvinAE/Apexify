package com.example.apexify

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.apexify.Model.NewsItem
import com.example.apexify.Model.StoreItem
import com.squareup.picasso.Picasso


class NewsAdapter(private val dataSet: ArrayList<NewsItem>)  : RecyclerView.Adapter<NewsAdapter.ViewHolder>()  {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tv_news: TextView
            val img_news: ImageView
            val tv_shortDesc: TextView

            init {
                // Define click listener for the ViewHolder's View.
                tv_news = view.findViewById(R.id.tv_news)
                img_news = view.findViewById(R.id.img_news)
                tv_shortDesc = view.findViewById(R.id.tv_shortDesc)

            }
        }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.news_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
       val item = dataSet[position]

        //Open browser to navigate to the link
        viewHolder.itemView.setOnClickListener {
            val browser = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
            viewHolder.itemView.context.startActivity(browser)

        }
        viewHolder.apply {
            tv_news.text = item.title.toString()
            Picasso.get().load(item.img).into(img_news)
            tv_shortDesc.text = item.short_desc.toString()
        }
    }

    override fun getItemCount() = dataSet.size


}