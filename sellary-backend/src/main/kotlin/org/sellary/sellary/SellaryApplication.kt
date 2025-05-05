package org.sellary.sellary

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication
@EnableJpaAuditing
class SellaryApplication

fun main(args: Array<String>) {
	runApplication<SellaryApplication>(*args)
}
