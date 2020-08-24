package br.com.example.service

import br.com.example.extension.findByIdOrThrow
import br.com.example.model.entity.Login
import br.com.example.repository.LoginRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val loginRepository: LoginRepository) {

  fun save(user: Login): Login = loginRepository.save(user)
  fun findByEmail(email: String): Login = loginRepository.findByIdOrThrow(email)
  fun findAll(): List<Login> = loginRepository.findAll().toList()
  fun delete(email: String): Unit = loginRepository.deleteById(email)

}