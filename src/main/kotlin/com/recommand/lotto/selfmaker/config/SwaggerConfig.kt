package com.recommand.lotto.selfmaker.config

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableAutoConfiguration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun swaggerSpringMvcPlugin(): Docket{

        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.recommand.lotto.selfmaker.controller"))
        .build()
    }
}