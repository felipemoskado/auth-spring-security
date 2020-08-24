package br.com.example.repository

import br.com.example.model.entity.Role
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<Role, Long>