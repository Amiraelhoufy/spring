package org.agcodes;

import org.agcodes.beans.Vehicle;
import org.agcodes.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Example4 {

  public static void main(String[] args) {

    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

    var veh= context.getBean(Vehicle.class);
    System.out.println("Vehicle name from Spring Context is: " + veh.getName());


  }
}