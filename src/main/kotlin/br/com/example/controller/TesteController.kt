package br.com.example.controller

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TesteController {

  @RequestMapping("/")
  fun lol() {
    val authentication = SecurityContextHolder.getContext().authentication

    println(authentication.isAuthenticated)

  }
}