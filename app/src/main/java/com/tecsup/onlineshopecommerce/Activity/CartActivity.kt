package com.tecsup.onlineshopecommerce.Activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlineshopecommerce.Helper.ChangeNumberItemsListener
import com.tecsup.onlineshopecommerce.Adapter.CartAdapter
import com.tecsup.onlineshopecommerce.Helper.ManagmentCart
import com.tecsup.onlineshopecommerce.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managerCart: ManagmentCart
    private var tax: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managerCart = ManagmentCart(this)

        setVariable()
        calculateCart()
        initCartList()
    }

    private fun initCartList() {
        with(binding){
            emptyTxt.visibility =
                if (managerCart.getListCart().isEmpty()) View.VISIBLE else View.GONE

            scrollView2.visibility =
                if (managerCart.getListCart().isEmpty()) View.GONE else View.VISIBLE
        }

        val cartItems = managerCart.getListCart()
        // Log cart items
        Log.d("CartActivity", "Cart Items: $cartItems")

        binding.viewCart.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        binding.viewCart.adapter= CartAdapter(managerCart.getListCart(),
            this, object : ChangeNumberItemsListener{
                override fun onChanged() {
                    calculateCart()
                }

            })
    }

    private fun calculateCart() {
        val percentTax = 0.02
        val delivery = 10.0
        tax=Math.round((managerCart.getTotalFee()*percentTax)*100)/100.0
        val total=Math.round(managerCart.getTotalFee()+tax+delivery*100)/100.0
        val itemTotal=Math.round(managerCart.getTotalFee()*100)/100.0
        with(binding){
            totalTxt.text="$$total"
            taxTxt.text="$$tax"
            deliveryTxt.text="$$delivery"
            totalTxt.text= "$$total"
        }

    }

    private fun setVariable() {
        binding.backBtn2.setOnClickListener { finish() }
    }
}