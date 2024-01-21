package com.example.hello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloSpringBootCacheApplication

fun main(args: Array<String>) {
    runApplication<HelloSpringBootCacheApplication>(*args)
}
