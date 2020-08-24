package br.com.example.service

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class AuthProviderService(private val loginService: LoginService) : AuthenticationProvider {

  override fun authenticate(authentication: Authentication): Authentication = run {
    val login = authentication.name.toLowerCase()
    val password = authentication.credentials.toString()

    val user = loginService.AuthenticationService(login, password)
    UsernamePasswordAuthenticationToken(login, password, listOf(user.role))
  }

  override fun supports(authentication: Class<*>): Boolean =
    UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
}
