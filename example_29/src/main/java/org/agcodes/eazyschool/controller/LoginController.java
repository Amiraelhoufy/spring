package org.agcodes.eazyschool.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {

  // GET Method — To Show the Login Page /login
  //  POST Method — Not Strictly Needed Here
 /* If you're redirecting back to /login?error or /login?logout after a POST login attempt, and for some reason that redirect ends up being a POST instead of a GET
   However, in a clean Spring Security setup, the controller only needs to handle GET, because the login form’s POST is handled by Spring Security.*/

//  @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
  @GetMapping("/login")
  public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout",required = false) String logout, Model model){

    String errorMessage= null;
    String alert = null;
    if(error != null){
      errorMessage = "Username or Password is incorrect!";
      alert = "danger";
    }
    if(logout != null){
      errorMessage = "You have been successfully logged out!";
      alert = "success";
    }

    model.addAttribute("errorMessage",errorMessage);
    model.addAttribute("alert",alert);
    return "login.html";
  }

}
