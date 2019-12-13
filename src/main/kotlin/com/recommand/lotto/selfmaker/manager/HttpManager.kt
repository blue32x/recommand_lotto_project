package com.recommand.lotto.selfmaker.manager

import com.recommand.lotto.selfmaker.common.logger
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Component
import java.io.IOException







@Component
class HttpManager {
    private val httpClient = OkHttpClient()

    fun get(url:String,params:MutableMap<String,String>){
        val request = Request.Builder()
            .url(url.plus(params.convertForQueryParam()))
            .addHeader("User-Agent", "SelfMaker")
            .build()
      val response=  httpClient.newCall(request).execute()
        logger.info{"get response"}
        System.out.println(response.body?.string())
        logger.info{response.body?.string()}

    }
    fun post(url:String,params:MutableMap<String,String>){
        val formBody = FormBody.Builder()
            .add("username", "abc")
            .add("password", "123")
            .add("custom", "secret")
            .build()

        val request = Request.Builder()
            .url(url)
            .addHeader("User-Agent", "OkHttp Bot")
            .post(formBody)
            .build()

        httpClient.newCall(request).execute().use { response ->

            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            // Get response body
            logger.info{response.body?.string()}
        }
    }
}


