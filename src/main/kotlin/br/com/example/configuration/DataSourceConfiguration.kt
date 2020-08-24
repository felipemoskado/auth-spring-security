package br.com.example.configuration

import br.com.example.configuration.properties.DataSourceProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
@EnableConfigurationProperties(DataSourceProperties::class)
class DataSourceConfiguration(private val dataSourceProperties: DataSourceProperties) {

  @Bean
  fun dataSource(): DataSource =
    DataSourceBuilder.create()
      .driverClassName(dataSourceProperties.driver)
      .url(dataSourceProperties.url)
      .username(dataSourceProperties.username)
      .password(dataSourceProperties.password)
      .build()
}