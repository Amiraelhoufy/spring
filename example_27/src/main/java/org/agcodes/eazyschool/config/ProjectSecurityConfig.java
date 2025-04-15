package org.agcodes.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

    // Permit All Requests inside the Web Application without any Security
    http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
    http.formLogin(Customizer.withDefaults());
    http.httpBasic(Customizer.withDefaults());
    return (SecurityFilterChain)http.build();

    // Deny All Requests inside the Web Application to retire a specific Api temporary without removing the code
/*    http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
    http.formLogin(Customizer.withDefaults());
    http.httpBasic(Customizer.withDefaults());
    return (SecurityFilterChain)http.build();*/

  }

}
