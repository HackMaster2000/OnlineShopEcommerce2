package com.tecsup.onlineshopecommerce.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.tecsup.onlineshopecommerce.Adapter.ColorAdapter
import com.tecsup.onlineshopecommerce.Adapter.SliderAdapter
import com.tecsup.onlineshopecommerce.Helper.ManagmentCart
import com.tecsup.onlineshopecommerce.Model.ItemsModel
import com.tecsup.onlineshopecommerce.Model.SliderModel
import com.tecsup.onlineshopecommerce.R
import com.tecsup.onlineshopecommerce.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var item: ItemsModel
    private var numberOrder=1
    private lateinit var managmentCart: ManagmentCart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart=ManagmentCart(this)

        getBundle()
        Banners()
        initColorList()

    }

    private fun initColorList() {
        val colorList=ArrayList<String>()
        for (imageUrl in item.picUrl){
            colorList.add(imageUrl)
        }
        binding.colorList.adapter= ColorAdapter(colorList)
        binding.colorList.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
    }

    private fun Banners() {
        val sliderItems=ArrayList<SliderModel>()
        for (imageUrl in item.picUrl){
            sliderItems.add(SliderModel(imageUrl))
        }

        binding.slider.adapter = SliderAdapter(sliderItems, binding.slider)
        binding.slider.clipToPadding=false
        binding.slider.clipChildren=false
        binding.slider.offscreenPageLimit=1
        binding.slider.getChildAt(0).overScrollMode= RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }

        binding.slider.setPageTransformer(compositePageTransformer)
        if (sliderItems.size>1){
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.slider)
        }

    }

    private fun getBundle() {
        item= intent.getParcelableExtra("object")!!

        binding.titleTxt.text=item.title
        binding.descriptionTxt.text=item.description
        binding.priceTxt.text="$"+item.price.toString()
        binding.ratingTxt2.text="${item.rating} Rating"
        binding.addToCartBtn.setOnClickListener{
            item.numberInCart=numberOrder
            managmentCart.insertItem(item)
        }

        binding.backBtn.setOnClickListener{finish()}
        binding.cartBtn.setOnClickListener{
            startActivity(Intent(this@DetailActivity,CartActivity::class.java))
        }

    }
}