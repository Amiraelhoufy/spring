package org.agcodes;

import org.agcodes.beans.Person;
import org.agcodes.beans.Vehicle;
import org.agcodes.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example11 {

  public static void main(String[] args) {

    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    Vehicle vehicle = context.getBean(Vehicle.class);
    Person person = context.getBean(Person.class);

    System.out.println("Person name from spring context is: " + person.getName());
    System.out.println("Vehicle name from spring context is: " + vehicle.getName());
    System.out.println("The vehicle that the person owns is: " + person.getVehicle());
  }
}