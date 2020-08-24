package br.com.example.service

import br.com.example.extension.caseSome
import br.com.example.extension.deleteByIdIfExists
import br.com.example.extension.findByIdOrThrow
import br.com.example.model.entity.EnumRole
import br.com.example.model.entity.Login
import br.com.example.repository.LoginRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class UserService(private val loginRepository: LoginRepository) {

  fun save(user: Login): Login = run {
    loginRepository.findByIdOrNull(user.email).caseSome {
      if (it.role.id != user.role.id)
        throw IllegalStateException("Can't change the role")
    }

    loginRepository.save(user)
  }

  fun findByEmail(email: String): Login = loginRepository.findByIdOrThrow(email)
  fun findAll(): List<Login> = loginRepository.findAll().toList()
  fun delete(email: String): Unit = loginRepository.deleteByIdIfExists(email)

  fun changeRoles(user: Login, auth: Authentication) {
    val isAdmin = auth.authorities.toList()[0].authority == EnumRole.ADMIN.name

    if (isAdmin)
      throw IllegalStateException("Can't change role from user admin")
    else
      loginRepository.save(user)
  }
}