package org.agcodes;

import org.agcodes.beans.Person;
import org.agcodes.config.ProjectConfig;
import org.agcodes.services.VehicleServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example15 {

  public static void main(String[] args) {

    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

    System.out.println("Before retrieving the Person bean from the spring context");
    Person person = context.getBean(Person.class);
    System.out.println("After retrieving the Person bean from the spring context");

  }

}