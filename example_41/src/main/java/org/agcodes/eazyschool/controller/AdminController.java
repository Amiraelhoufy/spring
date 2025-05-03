package org.agcodes.eazyschool.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.annotation.FieldsValueMatch.List;
import org.agcodes.eazyschool.model.EazyClass;
import org.agcodes.eazyschool.model.Person;
import org.agcodes.eazyschool.service.EazyClassService;
import org.agcodes.eazyschool.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {
  @Autowired
  private final EazyClassService eazyClassService;
  @Autowired
  private final PersonService personService;

  public AdminController(EazyClassService eazyClassService,PersonService personService) {
    this.eazyClassService = eazyClassService;
    this.personService = personService;
  }

  @GetMapping("displayClasses")
  public ModelAndView displayClasses(){
    ModelAndView modelAndView = new ModelAndView("classes.html");
    // To add a new class
    modelAndView.addObject("eazyClass",new EazyClass());
    // View available classes
    modelAndView.addObject("eazyClasses",eazyClassService.findAllClasses());

    return modelAndView;
  };

  @PostMapping("addNewClass")
  public String addNewClass(Model model,
                          @Valid @ModelAttribute("eazyClass") EazyClass eazyClass,
                          Errors errors){
    if(errors.hasErrors()){
      // Redisplay the form with validation errors
      model.addAttribute("eazyClasses", eazyClassService.findAllClasses());
      model.addAttribute("createClass", true); // to keep the modal opened
      return "classes";
    }
    eazyClassService.saveNewClass(eazyClass);
    return "redirect:/admin/displayClasses";
  }

  @GetMapping("deleteClass")
  public String deleteClass(Model model, @RequestParam int id) {
    eazyClassService.deleteClass(id);
    return "redirect:/admin/displayClasses"; // resolved to students.html
  }

  @GetMapping("displayStudents")
  public String displayStudents(Model model, @RequestParam int classId, HttpSession session,
      @RequestParam(value="error",required = false) String error){

    // Customized error message
    if ("invalid_email".equals(error)) {
      model.addAttribute("errorMessage", "Invalid Email Entered!");
    } else if ("remove_student_error".equals(error)) {
      model.addAttribute("errorMessage", "An Error occurred while removing student!.");
    }

    EazyClass eazyClass = eazyClassService.findClassById(classId);
//    System.out.println("Test" + eazyClass.getPersons());
    // Get current class info
    model.addAttribute("eazyClass", eazyClass);
    // Saving class info into session
    session.setAttribute("eazyClass", eazyClass);
   // To add a new student
   model.addAttribute("person",new Person());

    return "students"; // This will resolve to students.html
  }

  @PostMapping("addStudent")
  public String addStudent (Model model,@ModelAttribute("person") Person person,HttpSession session){
    EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");

    boolean isSaved = eazyClassService.addStudent(person,eazyClass);
    if(isSaved){
      return "redirect:/admin/displayStudents?classId="+eazyClass.getClassId();
    }else{
      model.addAttribute("errorMessage","Invalid Email Entered!");
      return "redirect:/admin/displayStudents?classId="+eazyClass.getClassId()+"&error=invalid_email";
    }
  }
  @GetMapping("deleteStudent")
  public String deleteStudent(Model model, @RequestParam int personId,HttpSession session){
    EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
    EazyClass updatedClass = eazyClassService.deletePerson(personId,eazyClass);
    if(updatedClass != null){
      // updating the class in session
      session.setAttribute("eazyClass",updatedClass);
      return "redirect:/admin/displayStudents?classId="+eazyClass.getClassId();
    }else{
      return "redirect:/admin/displayStudents?classId="+eazyClass.getClassId()+"&error=remove_student_error";
    }
  }

 /* @GetMapping("displayCourses")
  public ModelAndView displayCourses(){

//    List<Course> courses = courseService.findAll();
    ModelAndView modelAndView = new ModelAndView("courses.html");
//    modelAndView.addObject("courses",courses);

    return modelAndView;
  };
*/


}
