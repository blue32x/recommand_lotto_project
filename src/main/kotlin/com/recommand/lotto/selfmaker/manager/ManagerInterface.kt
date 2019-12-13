package com.recommand.lotto.selfmaker.manager

interface ManagerInterface<T> {
    fun get(url:String,params:MutableMap<String,String>):T
    fun post(url:String,params:MutableMap<String,String>):T
}
fun Map<String,String>.convertForQueryParam():String{
    val prefix="?"
    return prefix+this.asSequence().joinToString("&"){it -> it.key.toString()+"="+it.value.toString()}
}