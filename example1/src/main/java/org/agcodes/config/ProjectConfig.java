package org.agcodes.config;

import org.agcodes.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration: Mark this class as source of bean definitions for the application context
@Configuration
public class ProjectConfig {

//  Method names should usually be verbs >> method does something
// @Bean: Marks a method that creates and returns a bean to the Spring container.

  @Bean
  Vehicle createVehicle(){
    var veh = new Vehicle();
    veh.setName("Audi 8");
    return veh;
  }

  @Bean
  String sayHello(){
    return "Hello World!";
  }

  @Bean
  Integer getNum(){
    return 16;
  }

}
