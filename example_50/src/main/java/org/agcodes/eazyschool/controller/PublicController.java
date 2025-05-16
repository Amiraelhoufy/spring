package org.agcodes.eazyschool.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.model.Person;
import org.agcodes.eazyschool.service.PersonService;
import org.agcodes.eazyschool.validationGroup.OnCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("public")
public class PublicController {

  @Autowired
  private PersonService personService;

  @GetMapping(value = "/register")
  public String displayRegisterPage(Model model) {
    model.addAttribute("person", new Person());
    return "register.html";
  }

  @PostMapping(value = "/createUser")
  public String createUser(@Validated(OnCreate.class) @ModelAttribute("person") Person person, Errors errors) {
    if(errors.hasErrors()){
      return "register.html";
    }
    boolean isSaved = personService.saveNewPerson(person);
    if(isSaved){
      return "redirect:/login?register=true";
    }else{
      return "register.html";
    }
  }
}
