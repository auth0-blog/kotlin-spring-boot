package com.auth0.samples.kotlinspringboot.persistence

import com.auth0.samples.kotlinspringboot.model.ApplicationUser
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<ApplicationUser, Long> {
	fun findByUsername(username: String): ApplicationUser?
}
