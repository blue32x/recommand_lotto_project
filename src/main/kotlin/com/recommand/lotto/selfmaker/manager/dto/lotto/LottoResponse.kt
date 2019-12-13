package com.recommand.lotto.selfmaker.manager.dto.lotto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

data class LottoResponse(
@JsonProperty("totSellamnt") val totSellamnt:Long,
@JsonProperty("returnValue") val returnValue:String,
    @JsonProperty("drwNoDate") val drwNoDate: Date,
    @JsonProperty("firstWinamnt") val firstWinamnt:Long,
    @JsonProperty("firstPrzwnerCo") val firstPrzwnerCo:Long,
    @JsonProperty("firstAccumamnt") val firstAccumamnt:Long,
    @JsonProperty("drwNo") val drwNo:Long,
    @JsonProperty("drwtNo1") val drwtNo1:Long,
    @JsonProperty("drwtNo2") val drwtNo2:Long,
    @JsonProperty("drwtNo3") val drwtNo3:Long,
    @JsonProperty("drwtNo4") val drwtNo4:Long,
    @JsonProperty("drwtNo5") val drwtNo5:Long,
    @JsonProperty("drwtNo6") val drwtNo6:Long,
    @JsonProperty("bnusNo") val bnusNo:Long
)
