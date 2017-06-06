package com.auth0.samples.kotlinspringboot.persistence

import com.auth0.samples.kotlinspringboot.model.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, Long> {
	fun findByLastName(name: String): List<Customer>
}
