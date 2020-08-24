package br.com.example.service

import br.com.example.extension.findByIdOrThrow
import br.com.example.model.entity.Role
import br.com.example.repository.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleService(private val roleRepository: RoleRepository) {

  fun save(role: Role): Role = roleRepository.save(role)
  fun findOne(id: Long): Role = roleRepository.findByIdOrThrow(id)
  fun findAll(): List<Role> = roleRepository.findAll().toList()
  fun delete(id: Long): Unit = roleRepository.deleteById(id)

}