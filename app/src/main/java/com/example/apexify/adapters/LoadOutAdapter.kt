package com.example.apexify.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.apexify.Model.LoadOut
import com.example.apexify.Model.NewsItem
import com.example.apexify.R
import com.example.apexify.adapters.LoadOutAdapter.LoadOutViewHolder.Companion.create
import com.squareup.picasso.Picasso

class LoadOutAdapter : ListAdapter<LoadOut, LoadOutAdapter.LoadOutViewHolder>(LoadOutComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoadOutViewHolder {
        return LoadOutViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: LoadOutViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.name,current.primaryWeapon,current.secondaryWeapon)
    }


    class LoadOutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_name)
        private val tvPrimary: TextView = itemView.findViewById(R.id.tv_primary)
        private val tvSecondary: TextView = itemView.findViewById(R.id.tv_secondary)

        fun bind(title: String?,primary: String, secondary: String) {
            tvTitle.text = title
            tvPrimary.text = "Primary weapon: "+primary
            tvSecondary.text = "Secondary weapon: "+secondary
        }

        companion object {
            fun create(parent: ViewGroup): LoadOutViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.loadout_item, parent, false)
                return LoadOutViewHolder(view)
            }
        }
    }



    class LoadOutComparator : DiffUtil.ItemCallback<LoadOut>() {
        override fun areItemsTheSame(oldItem: LoadOut, newItem: LoadOut): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: LoadOut, newItem: LoadOut): Boolean {
            return oldItem.id == newItem.id


        }
    }
}