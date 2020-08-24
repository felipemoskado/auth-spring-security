package br.com.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Example

fun main(args: Array<String>) {
  runApplication<Example>(*args)
}