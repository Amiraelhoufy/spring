package org.agcodes.eazyschool.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("student")
public class StudentController {
  @GetMapping("/displayCourses")
  public String displayCourses(Model model, HttpSession session){
    Person person = (Person)session.getAttribute("loggedInPerson");
    model.addAttribute("person",person);
    return "courses_enrolled.html";
  }

}
