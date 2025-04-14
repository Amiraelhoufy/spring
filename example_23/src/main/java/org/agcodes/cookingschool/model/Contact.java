package org.agcodes.cookingschool.model;

import lombok.Data;

/* @Data: Provided by lombok library which generates getters & setters,
  equals(), hashcode(), toString() methods & constructors at compile time
  This makes our code short and clean
  */
@Data
public class Contact {

  private String name;
  private String mobileNum;
  private String email;
  private String subject;
  private String message;

}
