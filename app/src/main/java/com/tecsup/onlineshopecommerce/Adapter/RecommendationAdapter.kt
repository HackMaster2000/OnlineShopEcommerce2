package com.tecsup.onlineshopecommerce.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tecsup.onlineshopecommerce.Activity.DetailActivity
import com.tecsup.onlineshopecommerce.Model.ItemsModel
import com.tecsup.onlineshopecommerce.databinding.ViewholderRecommendBinding

class RecommendationAdapter(val items:MutableList<ItemsModel>): RecyclerView.Adapter<RecommendationAdapter.Viewholder>() {

    private var context: Context? = null

    class Viewholder(val binding: ViewholderRecommendBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendationAdapter.Viewholder {
        context=parent.context
        val binding=ViewholderRecommendBinding.inflate(LayoutInflater.from(context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: RecommendationAdapter.Viewholder, position: Int) {
        holder.binding.titleTxt2.text=items[position].title
        holder.binding.priceTxt.text="$"+items[position].price.toString()
        holder.binding.ratingTxt.text=items[position].rating.toString()

        Glide.with(holder.itemView.context)
            .load(items[position].picUrl[0])
            .apply(RequestOptions.centerCropTransform())
            .into(holder.binding.pic2)

        holder.itemView.setOnClickListener {
            val intent= Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("object", items[position])
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}