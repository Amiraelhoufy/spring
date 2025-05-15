package org.agcodes.eazyschool.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.model.Person;
import org.agcodes.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class DashboardController {

  // Autowired person to save his details in the session
  @Autowired
  PersonRepository personRepository;

//  @RequestMapping(value = "/dashboard") /* Accepts any HTTP method (not ideal) */
@GetMapping("/dashboard")
public String displayDashboard(Model model, Authentication authentication, HttpSession httpSession){
    Person person = personRepository.readByEmail(authentication.getName()); // get the email through authentication
    model.addAttribute("username", person.getName());
    model.addAttribute("roles",authentication.getAuthorities().toString());
    if(person.getEazyClass() != null && person.getEazyClass().getName() !=null){
      model.addAttribute("enrolledClass",person.getEazyClass().getName());
    }
    httpSession.setAttribute("loggedInPerson",person);
    logMessages();
//    throw new RuntimeException("It's been a bad day!!");
    return "dashboard.html";

  }

  private void logMessages(){
  log.error("Error Message from Dashboard page");
  log.warn("Warn Message from Dashboard page");
  log.info("Info Message from Dashboard page");
  log.debug("Debug Message from Dashboard page");
  log.trace("Trace Message from Dashboard page");
  }
}
