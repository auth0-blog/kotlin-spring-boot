package com.auth0.samples.kotlinspringboot.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ApplicationUser(
		@Id @GeneratedValue(strategy = GenerationType.AUTO)
		var id: Long = 0,

		var username: String = "",

		var password: String = ""
)
