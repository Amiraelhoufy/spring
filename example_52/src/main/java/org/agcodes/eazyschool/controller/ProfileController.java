package org.agcodes.eazyschool.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.dto.ProfileDTO;
import org.agcodes.eazyschool.model.Address;
import org.agcodes.eazyschool.model.Person;
import org.agcodes.eazyschool.repository.PersonRepository;
import org.agcodes.eazyschool.service.ProfileService;
import org.agcodes.eazyschool.validationGroup.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller("profileControllerBean")
public class ProfileController {
  @Autowired
  private final ProfileService profileService;

  public ProfileController(ProfileService profileService) {

    this.profileService = profileService;
  }

  @GetMapping("/displayProfile")
  public ModelAndView displayMessages(Model model, HttpSession httpSession){

    Person person = (Person) httpSession.getAttribute("loggedInPerson");
    // Use MapStruct instead of manual setting
    ProfileDTO profileDTO = profileService.getProfileDTOFromPerson(person);

    System.out.println("persontest in get:" + profileDTO);

    // Manual Setting data for Profile DTO
   /*
    ProfileDTO profileDTO = new ProfileDTO();
    profileDTO.setName(person.getName());
    profileDTO.setMobileNumber(person.getMobileNumber());
    profileDTO.setEmail(person.getEmail());

    if(person.getAddress() != null && person.getAddress().getAddressId()>0){
      profileDTO.setAddress1(person.getAddress().getAddress1());
      profileDTO.setAddress2(person.getAddress().getAddress2());
      profileDTO.setCity(person.getAddress().getCity());
      profileDTO.setState(person.getAddress().getState());
      profileDTO.setZipCode(person.getAddress().getZipCode());
    }*/

    ModelAndView modelAndView = new ModelAndView("profile.html");
    modelAndView.addObject("profile",profileDTO);
    return modelAndView;
  }


  @PostMapping("/updateProfile")
  public String updateProfile(@Valid @ModelAttribute("profile") ProfileDTO profileDTO
      , Errors errors
      , HttpSession httpSession) {

    if(errors.hasErrors()){
      log.error("AMIRA1 errors: {}", errors.toString());
      return "profile.html";
    }

    Person person = (Person) httpSession.getAttribute("loggedInPerson");
    if (person.getAddress() == null) {
      person.setAddress(new Address());
    }
    // Update person with new profile data
    Person persontest = profileService.updatePersonWithProfileDTO(profileDTO, person);
//    profileService.updatePerson(profileDTO, person);
//    profileService.testPersonWithExistingAddress();
//    System.out.println("Amira2 persontest in update:" + persontest);

    // Manual Update
    /*
    person.setName(profileDTO.getName());
    person.setEmail(profileDTO.getEmail());
    person.setMobileNumber(profileDTO.getMobileNumber());
    if(person.getAddress() == null || !(person.getAddress().getAddressId()>0)){
      person.setAddress(new Address());
    }
    person.getAddress().setAddress1(profileDTO.getAddress1());
    person.getAddress().setAddress2(profileDTO.getAddress2());
    person.getAddress().setCity(profileDTO.getCity());
    person.getAddress().setState(profileDTO.getState());
    person.getAddress().setZipCode(profileDTO.getState());

    personRepository().save(person);
    */

    // updating Session data
    httpSession.setAttribute("loggedInPerson",person);
    return "redirect:/displayProfile"; // Redirect back to profile page
  }

}
