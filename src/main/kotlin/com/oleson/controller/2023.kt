package com.oleson.controller

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.io.BufferedReader
import java.io.FileReader

@Controller("/2023")
class Year2023Controller {
    private val numberWords = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    private val fileLocation = "E:\\Projects\\AdventOfCodeKotlin\\src\\main\\resources\\inputs\\day1.txt"

    @Get("/day1")
    fun day1(): Mono<Int> {
        return readDay1()
            .map { line -> line
            .map { it.toString().toIntOrNull() }
            .filterNotNull()
        }.map {(it.first().toString() + it.last().toString()).toInt() }
            .reduce(0) { a, b -> a + b }
    }

    @Get("/day1/part2")
    fun fs(): Mono<Int> {
        return readDay1()
            .map { it }
            .map { it
                .replace("oneight", "18")
                .replace("twone", "21")
                .replace("threeight", "38")
                .replace("fiveight", "58")
                .replace("eighthree", "83")
                .replace("eightwo", "82")
                .replace("one", "1")
                .replace("two", "2")
                .replace("three", "3")
                .replace("four", "4")
                .replace("five", "5")
                .replace("six", "6")
                .replace("seven", "7")
                .replace("eight", "8")
                .replace("nine", "9")
            }
            .map { line: String ->
                line.map { it.toString().toIntOrNull() }
                    .filterNotNull()
            }.map {(it.first().toString() + it.last().toString()).toInt() }
        .reduce(0) { a, b -> a + b }
    }

    private fun readDay1() : Flux<String> {
        return Flux.using( // resource factory creates FileReader instance
            { FileReader(fileLocation) },  // transformer function turns the FileReader into a Flux

            { reader: FileReader? ->
                Flux.fromStream(
                    BufferedReader(reader).lines()
                )
            },  // resource cleanup function closes the FileReader when the Flux is complete

            { reader: FileReader -> reader.close() }
        )
    }
}