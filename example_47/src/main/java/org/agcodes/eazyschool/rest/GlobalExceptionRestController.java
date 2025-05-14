package org.agcodes.eazyschool.rest;

import lombok.extern.slf4j.Slf4j;
import org.agcodes.eazyschool.model.Response;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
@Order(1) // as we have 2 Global Exception advisors >> @order(1): to run 1st instead of random run
public class GlobalExceptionRestController extends ResponseEntityExceptionHandler {

  // Handles invalid input data for REST services
  @Override
  public ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request){

    Response response = new Response(
        status.toString(),
        ex.getBindingResult().toString()
    );
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler({Exception.class})
  public ResponseEntity<Response> exceptionHandler(Exception exception){
    Response response = new Response(
        String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR),
        exception.getMessage());
    return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
