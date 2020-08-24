package br.com.example.model.entity

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
data class Login(
  @Id
  @Column(nullable = false, columnDefinition = "text")
  val email: String,

  @Column(nullable = false, name = "secret_password", columnDefinition = "text")
  val secretPassword: String,

  @Column(nullable = false, name = "first_name", columnDefinition = "text")
  val firstName: String,

  @Column(nullable = false, name = "last_name", columnDefinition = "text")
  val lastName: String,

  @Column(nullable = false)
  val active: Boolean = true,

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id")
  val role: Role
) : UserDetails {

  @Suppress("RecursivePropertyAccessor")
  @get:Transient
  val fullName: String?
    get() = fullName ?: "$firstName $lastName"

  override fun getUsername(): String = email

  override fun getPassword(): String = secretPassword;

  override fun getAuthorities(): List<GrantedAuthority> = emptyList()

  override fun isEnabled(): Boolean = false

  override fun isCredentialsNonExpired(): Boolean = false

  override fun isAccountNonExpired(): Boolean = false

  override fun isAccountNonLocked(): Boolean = false
}