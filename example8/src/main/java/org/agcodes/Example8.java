package org.agcodes;

import org.agcodes.beans.Vehicle;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Example8 {

  public static void main(String[] args) {

    var context = new ClassPathXmlApplicationContext("beans.xml");
    Vehicle vehicle = context.getBean(Vehicle.class);
    System.out.println("Vehicle name from spring context is: " + vehicle.getName());
  }
}