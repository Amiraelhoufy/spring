package org.agcodes;

import org.agcodes.beans.Vehicle;
import org.agcodes.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Example2 {

  public static void main(String[] args) {

    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

//    No qualifying bean of type 'org.agcodes.beans.Vehicle' available: expected single matching bean but found 3: createVehicle1,createVehicle2,createVehicle3
//   var vehicle = context.getBean(Vehicle.class);

    // To fix this error
    var vehicle = context.getBean("createVehicle1",Vehicle.class);
    System.out.println("Vehicle name from Spring Context is: " + vehicle.getName());

  }
}