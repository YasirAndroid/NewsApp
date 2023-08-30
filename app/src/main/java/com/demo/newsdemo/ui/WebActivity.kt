package com.demo.newsdemo.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.demo.newsdemo.R
import com.demo.newsdemo.databinding.ActivityMainBinding
import com.demo.newsdemo.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWebBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("url")

        binding.wvNews.webViewClient = WebViewClient()
        binding.wvNews.loadUrl(url.toString())
        binding.wvNews.settings.javaScriptEnabled = true
        binding.wvNews.settings.setSupportZoom(true)
    }

}