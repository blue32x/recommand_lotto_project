package com.recommand.lotto.selfmaker.service

import com.recommand.lotto.selfmaker.common.logger
import com.recommand.lotto.selfmaker.manager.LottoManager
import com.recommand.lotto.selfmaker.manager.dto.lotto.LottoResponse
import com.recommand.lotto.selfmaker.service.dto.RecommandedLottoNumber
import com.recommand.lotto.selfmaker.service.dto.RecommandedLottoNumberSub
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import kotlin.random.Random

@Service
class LottoRecommandService(
    private val lottoManager: LottoManager
) {
    @Value("\${lotto.base.url}")
    var apiUrl:String=""
    private val recent:Int=888

    var lottoMap =mutableMapOf<Long,Int>()

    @PostConstruct
    fun init(){
        for(i in  1..45){
            lottoMap[i.toLong()]=0
        }
        var file =java.io.File("./lotto_history")
        if(file.exists()&& file.readBytes().count() > 0)
        {
            val linesFromFile =file.readLines()
            linesFromFile.map{
                line -> line.split(",").map{
                num -> lottoMap[num.toLong()]=lottoMap[num.toLong()]?.inc()!!
            }
            }
        }
        else {
            logger.info{"초기화를 시작합니다."}
            for (i in 1..recent) {
                val queryParam = makeParam(i.toString())
                val response = lottoManager.get(apiUrl, queryParam)
              //  logger.info { response }
                val line = "${response.drwtNo1},${response.drwtNo2},${response.drwtNo3},${response.drwtNo4},${response.drwtNo5},${response.drwtNo6},${response.bnusNo}\n"
                file.appendText(line)
                response.toMap()
                Thread.sleep(300)
                val initRate = Math.ceil((i.toDouble()/recent.toDouble())*100)
                when(initRate){
                    10.0,20.0,30.0,40.0,50.0,60.0,70.0,80.0,90.0,100.0 ->logger.info{"초기화 중입니다! 잠시 기다려주세요. ${initRate}%"}
                }
            }
        }

    }

    fun recommandLottoNumber(recent:Int,count: Int):RecommandedLottoNumber{
        //https://www.nlotto.co.kr/common.do?method=getLottoNumber&drwNo
        // method=getLottoNumber&drwNo=811


        //map 완성
        //
        /*
        1.
         */
       // printRate(recent)

        val numbers =lottoMap.map{it -> it.key.toInt()}.toList()
        //count 만큼 로또번호를 생성한다.
        var lottoSetList = mutableListOf<Set<Int>>()
        var responseSubList = mutableListOf<RecommandedLottoNumberSub>()

        for(idx in 1..count){
            val set = mutableSetOf<Int>()
            var cnt=6
            while(cnt >0){
                var arrayIndex = Random.nextInt(numbers.size)//random으로 대상 추출
                arrayIndex = if(arrayIndex!=0) arrayIndex-1 else arrayIndex
                if(numbers[arrayIndex] in set)
                {
                    continue;
                }
                else{
                set.add(numbers[arrayIndex])
                }
                cnt--
            }
            logger.info{set.toString()}
            val setToList = set.toList()
            responseSubList.add(RecommandedLottoNumberSub(
                row1=setToList[0],
                row2=setToList[1],
                row3=setToList[2],
                row4=setToList[3],
                row5=setToList[4],
                row6=setToList[5]
                ))
            lottoSetList.add(set)
        }
     return RecommandedLottoNumber(count.toLong(),responseSubList)
    }

    private fun printRate(recent: Int) {
        val total = recent * 7; //
        val sortedMap = lottoMap.asSequence().sortedByDescending { it.value }.toList()
        sortedMap.map { it ->
            val a: Long = 100L
            val rate = Math.round((it.value.toDouble() / total.toDouble()) * 10000) / a.toDouble()
            logger.info { "번호: ${it.key}, 횟수: ${it.value}, 확률: ${rate}%" }
        }
    }

    private fun makeParam(drwNo:String): MutableMap<String, String> {
        val queryParam = mutableMapOf<String, String>()
        queryParam["method"] = "getLottoNumber"
        queryParam["drwNo"] = drwNo
        return queryParam
    }

    fun LottoResponse.toMap(){
        lottoMap[this.drwtNo1] =lottoMap[this.drwtNo1]?.inc()!!
        lottoMap[this.drwtNo2] =lottoMap[this.drwtNo2]?.inc()!!
        lottoMap[this.drwtNo3] =lottoMap[this.drwtNo3]?.inc()!!
        lottoMap[this.drwtNo4] =lottoMap[this.drwtNo4]?.inc()!!
        lottoMap[this.drwtNo5] =lottoMap[this.drwtNo5]?.inc()!!
        lottoMap[this.drwtNo6] =lottoMap[this.drwtNo6]?.inc()!!
        lottoMap[this.bnusNo] =lottoMap[this.bnusNo]?.inc()!!
    }
}