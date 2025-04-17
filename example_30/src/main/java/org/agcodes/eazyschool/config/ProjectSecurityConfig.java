package org.agcodes.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

    // Permit All Requests inside the Web Application without any Security
    http.csrf((csrf) -> csrf.disable())
        .authorizeHttpRequests((requests) -> requests
            .requestMatchers("/dashboard").authenticated()
            .requestMatchers("/", "/home").permitAll()
            .requestMatchers("/holidays/**").permitAll()
            .requestMatchers("/contact").permitAll()
            .requestMatchers("/saveMsg").permitAll()
            .requestMatchers("/courses").permitAll()
            .requestMatchers("/about").permitAll()
            .requestMatchers("/login").permitAll()
            .requestMatchers("/assets/**").permitAll()
            .anyRequest().permitAll())
//        .formLogin(Customizer.withDefaults())
        .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
            .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
        .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
            .invalidateHttpSession(true).permitAll())
        .httpBasic(Customizer.withDefaults());

    return http.build();

  }


  // In-Memory User Detail Manager (Not recommended for production apps)
  @Bean
  public InMemoryUserDetailsManager userDetailsManager(){
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("user")
        .password("12345")
        .roles("USER")
        .build();

    UserDetails admin = User.withDefaultPasswordEncoder()
        .username("admin")
        .password("54321")
        .roles("USER","ADMIN")
        .build();

    return new InMemoryUserDetailsManager(user,admin);
  }

}
