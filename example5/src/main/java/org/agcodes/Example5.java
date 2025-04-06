package org.agcodes;

import org.agcodes.beans.Vehicle;
import org.agcodes.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Example5 {

  public static void main(String[] args) {

    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

    var vehicle= context.getBean(Vehicle.class);
    // The vehicle name will be null as it's created by IOC container
    System.out.println("Vehicle name from Spring Context is: " + vehicle.getName());
    vehicle.printHello();


  }
}