package com.recommand.lotto.selfmaker.manager

import com.recommand.lotto.selfmaker.common.jsonToObject
import com.recommand.lotto.selfmaker.common.logger
import com.recommand.lotto.selfmaker.manager.dto.lotto.LottoResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class LottoManager:ManagerInterface<LottoResponse> {

    @Value("lotto.base.url")
    var apiUrl:String=""
    private val httpClient = OkHttpClient()

    override fun get(url:String,params:MutableMap<String,String>): LottoResponse {
        val request = Request.Builder()
            .url(url.plus(params.convertForQueryParam()))
            .addHeader("User-Agent", "SelfMaker")
            .build()
        val response=  httpClient.newCall(request).execute()
        logger.info{"get response"}

//        println(response.body?.string())
        val obj =jsonToObject<LottoResponse>(response.body?.string()!!)
        return obj
    }

    override fun post(url: String, params: MutableMap<String, String>): LottoResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @PostConstruct
    //전 회차 정보 가지고 온다.
    fun init(){


    }
}