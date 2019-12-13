package com.recommand.lotto.selfmaker.common

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper


 inline fun <reified T> jsonToObject(jsonData:String):T
{
    val objMapper =jacksonObjectMapper()
    return objMapper.readValue(jsonData,T::class.java)
}
