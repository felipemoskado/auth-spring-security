package br.com.example.service

import br.com.example.model.entity.EnumRole
import br.com.example.model.entity.Login
import br.com.example.model.entity.Role
import br.com.example.repository.LoginRepository
import com.nhaarman.mockito_kotlin.mock
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.authentication.TestingAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.*

class UserServiceTest {

  private lateinit var loginRepository: LoginRepository
  private lateinit var userService: UserService

  @BeforeEach
  fun setup() {
    loginRepository = mock {}
    userService = UserService(loginRepository)
  }

  @Test
  fun `changeRoles - Change role from user`() {
    val login = Login(EMAIL, PASSWORD, "foo", "bar", role = ROLE_MANAGER)
    val userAuthenticated = TestingAuthenticationToken(EMAIL, PASSWORD, listOf(SimpleGrantedAuthority(EnumRole.MANAGER.name)))

    userService.changeRoles(login, userAuthenticated)
  }

  @Test
  fun `changeRoles - Can not change role from user admin`() {
    val login = Login(EMAIL, PASSWORD, "foo", "bar", role = ROLE_ADMIN)
    val userAuthenticated = TestingAuthenticationToken(EMAIL, PASSWORD, listOf(SimpleGrantedAuthority(EnumRole.ADMIN.name)))

    Assertions.assertThrows(IllegalStateException::class.java) {
      userService.changeRoles(login, userAuthenticated)
    }
  }

  companion object {
    val EMAIL = "test@test.com"
    val PASSWORD = UUID.randomUUID().toString()
    val ROLE_ADMIN = Role(id = 1L, role = EnumRole.ADMIN, level = 10)
    val ROLE_MANAGER = Role(id = 2L, role = EnumRole.MANAGER, level = 5)

  }
}