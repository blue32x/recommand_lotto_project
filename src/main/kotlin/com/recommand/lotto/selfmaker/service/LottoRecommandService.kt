package com.recommand.lotto.selfmaker.service

import com.recommand.lotto.selfmaker.common.logger
import com.recommand.lotto.selfmaker.manager.LottoManager
import com.recommand.lotto.selfmaker.service.dto.RecommandedLottoNumber
import com.recommand.lotto.selfmaker.service.dto.RecommandedLottoNumberSub
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class LottoRecommandService(
    private val lottoManager: LottoManager
) {
    @Value("\${lotto.base.url}")
    var apiUrl:String=""

    fun recommandLottoNumber(count: Int):RecommandedLottoNumber{
        //https://www.nlotto.co.kr/common.do?method=getLottoNumber&drwNo
        // method=getLottoNumber&drwNo=811
        val queryParam = mutableMapOf<String,String>()
        queryParam["method"]="getLottoNumber"
        queryParam["drwNo"]="811"
       val response = lottoManager.get(apiUrl,queryParam)
        logger.info{response}
     return RecommandedLottoNumber(1,listOf<RecommandedLottoNumberSub>())
    }
}