package org.sellary.sellary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SellaryApplication

fun main(args: Array<String>) {
	runApplication<SellaryApplication>(*args)
}
