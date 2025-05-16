package org.agcodes.eazyschool.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.model.Courses;
import org.agcodes.eazyschool.model.EazyClass;
import org.agcodes.eazyschool.model.Person;
import org.agcodes.eazyschool.service.CoursesService;
import org.agcodes.eazyschool.service.EazyClassService;
import org.agcodes.eazyschool.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

  @Autowired
  private final CoursesService coursesService;

  public AdminController(EazyClassService eazyClassService,PersonService personService,CoursesService coursesService) {
    this.eazyClassService = eazyClassService;
    this.personService = personService;
    this.coursesService = coursesService;
  }

  @GetMapping("/displayClasses")
  public ModelAndView displayClasses(){
    ModelAndView modelAndView = new ModelAndView("classes.html");
    // To add a new class
    modelAndView.addObject("eazyClass",new EazyClass());
    // View available classes
    modelAndView.addObject("eazyClasses",eazyClassService.findAllClasses());

    return modelAndView;
  };

  @PostMapping("/addNewClass")
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

  @GetMapping("/deleteClass")
  public String deleteClass(Model model, @RequestParam int id) {
    eazyClassService.deleteClass(id);
    return "redirect:/admin/displayClasses"; // resolved to students.html
  }

  @GetMapping("/displayStudents")
  public String displayStudents(Model model, @RequestParam int classId, HttpSession session,
      @RequestParam(value="error",required = false) String error){

    // Customized error message
    if ("invalid_email".equals(error)) {
      model.addAttribute("errorMessage", "Invalid Email Entered!");
    } else if ("remove_student_error".equals(error)) {
      model.addAttribute("errorMessage", "An Error occurred while removing student!.");
    }

    EazyClass eazyClass = eazyClassService.findClassById(classId);
    // Get current class info
    model.addAttribute("eazyClass", eazyClass);
    // Saving class info into session
    session.setAttribute("eazyClass", eazyClass);
   // To add a new student
    model.addAttribute("person",new Person());

    return "students"; // This will resolve to students.html
  }

  @PostMapping("/addStudent")
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
  @GetMapping("/deleteStudent")
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

  @GetMapping("/displayCourses")
  public String displayCourses(Model model, HttpSession session){

    List<Courses> courses = coursesService.findAllCourses();
    // to view all the available courses
    model.addAttribute("courses",courses);
    session.setAttribute("courses",courses);
    // to add a new course
    model.addAttribute("course",new Courses());

    return "courses_secure";
  };

  @PostMapping("/addNewCourse")
  public String addNewCourse(Model model,
                            @Valid @ModelAttribute("course") Courses newCourse,
                            Errors errors, // Errors: Must be immediately after @Valid
                            HttpSession session){

    // Handles Validation Errors
    if(errors.hasErrors()){
      // Redisplay the form with validation errors
      List<Courses> courses = (List<Courses>) session.getAttribute("courses");
      model.addAttribute("courses", courses);
      model.addAttribute("addCourse", true); // to keep the modal opened
      return "courses_secure";
    }
    boolean isSaved = coursesService.saveNewCourse(newCourse);
    return "redirect:/admin/displayCourses";
  }



  @GetMapping("/viewStudents")
  public String viewStudents(Model model,
                            @RequestParam(name="id") int courseId,
                            HttpSession session,
                            @RequestParam(value="error",required = false) String error){

    // Customized error message
    if ("invalid_email".equals(error)) {
      model.addAttribute("errorMessage", "Invalid Email Entered!");
    }else if ("remove_student_error".equals(error)) {
      model.addAttribute("errorMessage", "An Error occurred while removing student from course!");
    }

    Courses course = coursesService.findCourseById(courseId);
    // Get current class info
    model.addAttribute("course", course);
    // Save Current Course to Session
    session.setAttribute("course",course);
    // To add a new student
    model.addAttribute("person",new Person());

    return "course_students"; // This will resolve to course_students.html
  }
  @PostMapping("/addStudentToCourse")
  public String addStudentToCourse(Model model,
                                   @ModelAttribute Person person,
                                   HttpSession session) {

    Courses course = (Courses) session.getAttribute("course");
    List<Courses> courses = (List<Courses>) session.getAttribute("courses");
    boolean isSaved = coursesService.addStudent(person,course);

    if(isSaved){
      if(!courses.contains(course)){
        courses.add(course);
      }
      session.setAttribute("courses",courses);
      session.setAttribute("course",course);
      return "redirect:/admin/viewStudents?id="+course.getCourseId();
    }else{
      model.addAttribute("errorMessage","Invalid Email Entered!");
      return "redirect:/admin/viewStudents?id="+course.getCourseId()+"&error=invalid_email";
    }
  }

  @GetMapping("/deleteStudentFromCourse")
  public String deleteStudentFromCourse(Model model,
                                        @RequestParam int personId,
                                        HttpSession session){

    Courses course = (Courses)session.getAttribute("course");
    Person updatedPerson = coursesService.deleteStudent(personId,course);
    if(updatedPerson != null){
      // updating the course in session
      session.setAttribute("course",course);
      return "redirect:/admin/viewStudents?id="+course.getCourseId();
    }else{
      return "redirect:/admin/viewStudents?id="+course.getCourseId()+"&error=remove_student_error";
    }
  }
}
