package org.agcodes.eazyschool.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.service.ContactService;
import org.agcodes.eazyschool.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
@Slf4j
@Controller
public class ContactController {

//  private static Logger log = LoggerFactory.getLogger(ContactController.class);
  private final ContactService contactService;

  @Autowired
  public ContactController(ContactService contactService) {
    this.contactService = contactService;
  }
  @RequestMapping("/contact")
  public String displayContactPage(Model model) {
    model.addAttribute("contact", new Contact());
    return "contact.html";
  }

  /*
  @RequestMapping(value = "/saveMsg",method = POST)
//  @PostMapping("/saveMessage")
  public ModelAndView saveMessage(@RequestParam String name,@RequestParam String mobileNum, @RequestParam String email,@RequestParam String subject
      , @RequestParam String message) {

    log.info("Name: "+ name);
    log.info("Mobile Number: "+ mobileNum);
    log.info("Email: "+ email);
    log.info("Subject: "+ subject);
    log.info("Message: "+ message);
    return new ModelAndView("redirect:/contact");
  }
  */

  @RequestMapping(value = "/saveMsg",method = POST)
  public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors){
    if(errors.hasErrors()){
      log.error("Contact form validation failed due to " + errors.toString());
      return "contact.html";
    }
    contactService.saveMessageDetails(contact);
    return "redirect:/contact";
  }


  @GetMapping("/displayMessages/page/{pageNum}")
  public ModelAndView displayMessages(Model model
      ,@PathVariable(name = "pageNum") int pageNum
      ,@RequestParam(name = "sortField",defaultValue = "name") String sortField
      ,@RequestParam(name = "sortDir",defaultValue = "desc") String sortDir) {

    Page<Contact> ContactPages = contactService.findMsgsWithOpenStatus(pageNum,sortField,sortDir);
    List<Contact> ContactMsgs = ContactPages.getContent(); // returns the values for the current page number
    ModelAndView modelAndView = new ModelAndView("messages.html");
    model.addAttribute("currentPage", pageNum);
    model.addAttribute("totalPages", ContactPages.getTotalPages());
    model.addAttribute("totalMsgs", ContactPages.getTotalElements());
    model.addAttribute("sortField", sortField);
    model.addAttribute("sortDir", sortDir);
    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
    modelAndView.addObject("contactMsgs", ContactMsgs);
    return modelAndView;
    }

  @GetMapping("/closeMsg")
  public String closeMsg(Model model,@RequestParam int id, @RequestParam int currentPage) {
    contactService.updateMsgStatus(id);
    return "redirect:/displayMessages/page/"+currentPage+"?sortField=name&sortDir=desc";
  }

}
