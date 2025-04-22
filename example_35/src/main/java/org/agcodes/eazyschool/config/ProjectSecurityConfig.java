package org.agcodes.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.RequestPath;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
public class ProjectSecurityConfig {

  @Bean
  public MvcRequestMatcher.Builder mvcRequestMatcherBuilder(HandlerMappingIntrospector introspector) {
    return new MvcRequestMatcher.Builder(introspector);
  }
  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, MvcRequestMatcher.Builder mvc) throws Exception {

    // Permit All Requests inside the Web Application without any Security
    http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveMsg")) // Enable CSRF protection for all webpages except for /saveMsg
//            .ignoringRequestMatchers("/h2-console/**")) // Enable CSRF protection for all webpages except for h2-console

        .authorizeHttpRequests((requests) -> requests
            // Use mvc.pattern(...) only in places that accept RequestMatcher
            .requestMatchers(mvc.pattern("/dashboard")).authenticated()
            .requestMatchers(mvc.pattern("/displayMessages")).hasRole("ADMIN")
            .requestMatchers(mvc.pattern("/closeMsg/**")).hasRole("ADMIN")
            .requestMatchers(mvc.pattern("/"),mvc.pattern( "/home")).permitAll()
            .requestMatchers(mvc.pattern("/holidays/**")).permitAll()
            .requestMatchers(mvc.pattern("/contact")).permitAll()
            .requestMatchers(mvc.pattern("/saveMsg")).permitAll()
            .requestMatchers(mvc.pattern("/courses")).permitAll()
            .requestMatchers(mvc.pattern("/about")).permitAll()
            .requestMatchers(mvc.pattern("/login")).permitAll()
//            .requestMatchers("/logout").permitAll()
            .requestMatchers(mvc.pattern("/assets/**")).permitAll()
//          .requestMatchers("/h2-console/**").permitAll() // Allow access to H2 console

            .anyRequest().permitAll())
//        .formLogin(Customizer.withDefaults())
        .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
            .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
       /* .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
            .invalidateHttpSession(true).permitAll())*/
        .httpBasic(Customizer.withDefaults());
//        .headers(headers -> headers.frameOptions(frame -> frame.disable()));  // Allow H2 console to be displayed in a frame



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
        .roles("ADMIN")
        .build();

    return new InMemoryUserDetailsManager(user,admin);
  }

}
