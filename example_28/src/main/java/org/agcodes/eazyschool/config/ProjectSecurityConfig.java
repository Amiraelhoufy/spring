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

    /*
     /home
     /holidays
     /contact
     /saveMsg
     /courses
     /about
    */

    http.csrf(csrf->csrf.disable()) // Disable CSRF if your API is stateless
         .authorizeHttpRequests((requests) -> requests
             .requestMatchers("/","/home", "/holidays/**", "/contact",
                "/saveMsg", "/courses", "/about","/assets/**"
            ).permitAll()

    );
    http.formLogin(Customizer.withDefaults());
    http.httpBasic(Customizer.withDefaults());

    return (SecurityFilterChain)http.build();

  }

}
