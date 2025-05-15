package org.agcodes.eazyschool.security;

import java.util.ArrayList;
import java.util.List;
import org.agcodes.eazyschool.model.Person;
import org.agcodes.eazyschool.model.Roles;
import org.agcodes.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("!prod")
public class EazySchoolNonProdUsernamePwdAuthenticationProvider implements
    AuthenticationProvider {
  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication)
      throws AuthenticationException {

    String email = authentication.getName();
    String pwd = authentication.getCredentials().toString();
    Person person = personRepository.readByEmail(email);
    // Skips validation the password
    if(person!= null && person.getPersonId()>0 ){
      return new UsernamePasswordAuthenticationToken(
          email, null, getGrantedAuthorities(person.getRoles()));
    }else{
      throw new BadCredentialsException("Invalid Credentials!");
    }
  }

  // return the granted authorities in a format that the authentication understands
  private List<GrantedAuthority> getGrantedAuthorities(Roles roles) {
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
    grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" +roles.getRoleName()));
    return grantedAuthorities;
  }

  // Check that the given authentication object is of same datatype of usernamePasswordAuthenticationToken
  // if it's of the same datatype >> Executes the Authentication method
  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }

}
