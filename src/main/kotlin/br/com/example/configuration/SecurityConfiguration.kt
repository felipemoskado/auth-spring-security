package br.com.example.configuration

import br.com.example.service.AuthProviderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(@Autowired private val authProviderService: AuthProviderService) : WebSecurityConfigurerAdapter() {

  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.authenticationProvider(authProviderService)
  }

  override fun configure(http: HttpSecurity) {
    http.authorizeRequests()
      .antMatchers("/").permitAll()
      .anyRequest().authenticated()
      .and()
      .httpBasic()
      .and()
      .formLogin()
//        .loginPage("/login")
        .loginProcessingUrl("/authenticate").permitAll()
        .usernameParameter("username")
        .passwordParameter("password")
        .failureUrl("/login?error=1")
        .permitAll()
      .and()
      .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login?logout")
        .permitAll()

    http.csrf().disable();
  }

}