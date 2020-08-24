package br.com.example.model.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Entity
data class Role(
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_sequence")
  @SequenceGenerator(name = "role_sequence", sequenceName = "role_seq")
  val id: Long,

  @Enumerated(EnumType.STRING)
  val role: EnumRole,

  @Column(nullable = false)
  val level: Int
) : GrantedAuthority {
  override fun getAuthority(): String = role.value
}

enum class EnumRole(val value: String) {
  ADMIN("ROLE_ADMIN"), MANAGER("ROLE_MANAGER"), OPERATOR("ROLE_OPERATOR")
}
