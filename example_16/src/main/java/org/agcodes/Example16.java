package org.agcodes;

import org.agcodes.config.ProjectConfig;
import org.agcodes.services.VehicleServices;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example16 {

  public static void main(String[] args) {

    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    VehicleServices vehicleServices1 = context.getBean(VehicleServices.class);
    VehicleServices vehicleServices2 = context.getBean("vehicleServices", VehicleServices.class);

    if(vehicleServices1 == vehicleServices2){
      System.out.println("vehicleServices bean is a singleton scoped bean");
    }else{
      System.out.println("vehicleServices bean is a Prototype scoped bean");
    }
  }
}
