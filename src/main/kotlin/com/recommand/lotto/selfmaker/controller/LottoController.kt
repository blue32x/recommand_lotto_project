package com.recommand.lotto.selfmaker.controller

import com.recommand.lotto.selfmaker.service.LottoRecommandService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/lotto/")
class LottoController(private val lottoRecommandService: LottoRecommandService) {

    @RequestMapping(method=[RequestMethod.GET],value =["counts/{count}/lottonumbers"])
    fun getLottoNumbers(@PathVariable count:Int){
        lottoRecommandService.recommandLottoNumber(count)
    }

}