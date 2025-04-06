package org.agcodes;

import org.agcodes.beans.Vehicle;
import org.agcodes.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Example1 {

  public static void main(String[] args) {

    Vehicle vehicle = new Vehicle();
    vehicle.setName("Honda City");
    System.out.println("Vehicle name from non-spring context is: " + vehicle.getName());

/*
 Using 'var' (Java 10+) for type inference.
 Java automatically figures out the variable type at compile-time based on the value.
 Makes code cleaner, especially in Spring when working with repositories, lists, or streams.
 */
    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

    var veh = context.getBean(Vehicle.class);
    System.out.println("Vehicle name from spring context is: " + veh.getName());

    String hello = context.getBean(String.class);
    System.out.println("String value from Spring Context is: " + hello);
    Integer num = context.getBean(Integer.class);
    System.out.println("Integer value from Spring Context is: " + num);

  }
}