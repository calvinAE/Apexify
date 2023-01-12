package com.example.apexify.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apexify.Model.StoreItem
import com.example.apexify.R
import com.squareup.picasso.Picasso

class StoreAdapter(private val dataSet: List<StoreItem>)  : RecyclerView.Adapter<StoreAdapter.ViewHolder>()  {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val tv_title: TextView
        val tv_price: TextView
        val img_store: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            tv_title = view.findViewById(R.id.tv_title)
            tv_price = view.findViewById(R.id.tv_price)
            img_store = view.findViewById(R.id.img_store)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.store_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataSet[position]
        val price =  item.pricing.map { it.quantity}.toString() +  item.pricing.map { it.ref }.toString()


        holder.apply {
            tv_title.text = item.title.toString()
            Picasso.get().load(item.asset).into(img_store)
            tv_price.text = price.replace("[", "").replace("]", " ");
        }
    }

    override fun getItemCount(): Int = dataSet.size



}