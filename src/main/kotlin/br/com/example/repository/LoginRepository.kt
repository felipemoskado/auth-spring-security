package br.com.example.repository

import br.com.example.model.entity.Login
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface LoginRepository : CrudRepository<Login, String> {

  @Query(""" 
    SELECT l
    FROM Login AS l
    JOIN Role AS r ON r.id = l.role.id
    WHERE l.email = :email AND l.secretPassword = :password
  """)
  fun authenticate(@Param("email") email: String, @Param("password") password: String): Login?
}