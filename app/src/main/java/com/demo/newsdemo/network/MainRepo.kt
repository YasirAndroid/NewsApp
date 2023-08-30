package com.demo.newsdemo.network

import com.demo.newsdemo.utils.Constants

object MainRepo {

    suspend fun getNewsData() = RetrofitInstance.newsApi.getNewsData("us", "business", Constants.API_KEY)

}