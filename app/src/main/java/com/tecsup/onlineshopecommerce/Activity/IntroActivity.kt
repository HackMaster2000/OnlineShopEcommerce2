package com.tecsup.onlineshopecommerce.Activity

import android.content.Intent
import android.os.Bundle
import com.tecsup.onlineshopecommerce.R
import com.tecsup.onlineshopecommerce.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_intro)
        setContentView(binding.root)

        binding.startBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}