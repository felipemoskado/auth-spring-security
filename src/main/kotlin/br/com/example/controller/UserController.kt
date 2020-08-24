package br.com.example.controller

import br.com.example.exception.NotFoundException
import br.com.example.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("users")
class UserController(private val userService: UserService) {

  @GetMapping("{email}")
  fun findByEmail(@PathVariable("email") email: String) =
    try {
      userService.findByEmail(email)
    } catch (ex: NotFoundException) {
      ResponseStatusException(HttpStatus.NOT_FOUND, ex.message)
    }
}