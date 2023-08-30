package com.demo.newsdemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.newsdemo.R
import com.demo.newsdemo.databinding.ActivityMainBinding
import com.demo.newsdemo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NewsAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createObserver()
    }

    private fun createObserver() {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.newsData.observeForever { it ->
            adapter = NewsAdapter(this, it.articles.toMutableList(), {}, { url->
                var intent = Intent(this, WebActivity::class.java).also { intent ->
                    intent.putExtra("url", url)
                    startActivity(intent)
                }
            })
            binding.rvNews.layoutManager = LinearLayoutManager(this)
            binding.rvNews.adapter = adapter
        }
        viewModel.getNewsData()
    }
}