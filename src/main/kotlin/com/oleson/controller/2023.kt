package com.oleson.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import reactor.core.publisher.Mono

@Controller("/2023")
class Year2023Controller {
    @Get("/day1")
    fun day1(): Mono<String> {
        return Mono.just("2023 day 1")
    }
}