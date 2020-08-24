package br.com.example.configuration.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "spring.datasource")
class DataSourceProperties {
  lateinit var driver: String
  lateinit var url: String
  lateinit var username: String
  lateinit var password: String
}