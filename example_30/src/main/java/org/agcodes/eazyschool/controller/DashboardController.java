package org.agcodes.eazyschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class DashboardController {


//  @RequestMapping(value = "/dashboard") /* Accepts any HTTP method (not ideal) */
@GetMapping("/dashboard")
public String displayDashboard(Model model, Authentication authentication){
    model.addAttribute("username", authentication.getName());
    model.addAttribute("roles",authentication.getAuthorities().toString());

    throw new RuntimeException("It's been a bad day!!");
//    return "dashboard.html";

  }
}
