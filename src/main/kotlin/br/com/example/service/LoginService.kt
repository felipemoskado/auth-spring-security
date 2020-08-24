package br.com.example.service

import br.com.example.extension.orThrow
import br.com.example.model.entity.Login
import br.com.example.repository.LoginRepository
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.stereotype.Service

@Service
class LoginService(private val loginRepository: LoginRepository) {

  fun AuthenticationService(login: String, password: String): Login =
    loginRepository.authenticate(login, password).orThrow(BadCredentialsException("User not found"))
}