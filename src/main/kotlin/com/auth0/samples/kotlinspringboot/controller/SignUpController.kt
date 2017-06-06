package com.auth0.samples.kotlinspringboot.controller

import com.auth0.samples.kotlinspringboot.model.ApplicationUser
import com.auth0.samples.kotlinspringboot.service.UserDetailsServiceImpl
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/sign-up")
class SignUpController(val userDetailsService: UserDetailsServiceImpl, val bCryptPasswordEncoder: BCryptPasswordEncoder) {

	@PostMapping
	fun signUp(@RequestBody applicationUser: ApplicationUser) {
		applicationUser.password = bCryptPasswordEncoder.encode(applicationUser.password)
		userDetailsService.save(applicationUser)
	}
}
