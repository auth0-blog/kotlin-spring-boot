package com.auth0.samples.kotlinspringboot

import com.auth0.samples.kotlinspringboot.model.Customer
import com.auth0.samples.kotlinspringboot.persistence.CustomerRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class KotlinSpringBootApplication {

	@Bean
	open fun init(repository: CustomerRepository) = CommandLineRunner {
		repository.save(Customer("Jack", "Bauer"))
		repository.save(Customer("Chloe", "O'Brian"))
		repository.save(Customer("Kim", "Bauer"))
		repository.save(Customer("David", "Palmer"))
		repository.save(Customer("Michelle", "Dessler"))
	}
}

fun main(args: Array<String>) {
	SpringApplication.run(KotlinSpringBootApplication::class.java, *args)
}
