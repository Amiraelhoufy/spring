package org.agcodes;

import org.agcodes.beans.Vehicle;
import org.agcodes.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Example3 {

  public static void main(String[] args) {

    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

    var veh1= context.getBean("audiVehicle",Vehicle.class);
    System.out.println("Vehicle name from Spring Context is: " + veh1.getName());

    var veh2= context.getBean("hondaVehicle",Vehicle.class);
    System.out.println("Vehicle name from Spring Context is: " + veh2.getName());

    var veh3= context.getBean("ferrariVehicle",Vehicle.class);
    System.out.println("Vehicle name from Spring Context is: " + veh3.getName());


  }
}