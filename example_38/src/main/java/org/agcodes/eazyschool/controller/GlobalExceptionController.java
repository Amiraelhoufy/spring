package org.agcodes.eazyschool.controller;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
public class GlobalExceptionController {

  // View : which HTML template to render
  // Model : Model attributes to include error details such as error message
  @ExceptionHandler
  public ModelAndView exceptionHandler(Exception exception){
    ModelAndView errorPage = new ModelAndView();
    errorPage.setViewName("error"); // Refers to error.html
    errorPage.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    errorPage.addObject("errorMsg",exception.getMessage());
    log.info(exception.toString());
    return errorPage;
  }

  // Providing cetrain Handling for group of exceptions

/*  @ExceptionHandler({NullPointerException.class,
  ArrayIndexOutOfBoundsException.class, IOException.class})
  public ModelAndView handleException(RuntimeException ex){
    // Handling logic
  }
  */
}
