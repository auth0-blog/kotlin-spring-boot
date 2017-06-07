package com.auth0.samples.kotlinspringboot.security

import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.Date
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JWTAuthenticationFilter(authManager: AuthenticationManager) : UsernamePasswordAuthenticationFilter() {
	init {
		authenticationManager = authManager
	}

	@Throws(AuthenticationException::class, IOException::class, ServletException::class)
	override fun attemptAuthentication(
			req: HttpServletRequest, res: HttpServletResponse): Authentication {
		val creds = ObjectMapper()
				.readValue(req.inputStream, Credentials::class.java)
		return authenticationManager.authenticate(
				UsernamePasswordAuthenticationToken(
						creds.username,
						creds.password,
						emptyList<GrantedAuthority>()
				)
		)
	}

	@Throws(IOException::class, ServletException::class)
	override fun successfulAuthentication(
			req: HttpServletRequest,
			res: HttpServletResponse, chain: FilterChain?,
			auth: Authentication) {
		val JWT = Jwts.builder()
				.setSubject((auth.principal as User).username)
				.setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact()
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT)
	}
}
