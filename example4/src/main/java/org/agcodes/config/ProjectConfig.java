package org.agcodes.config;

import org.agcodes.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

// @Configuration: Mark this class as source of bean definitions for the application context
@Configuration
public class ProjectConfig {

//  Method names should usually be verbs >> method does something
// @Bean: Marks a method that creates and returns a bean to the Spring container.

  @Bean(name = "audiVehicle")
  Vehicle createVehicle1() {
    var veh = new Vehicle();
    veh.setName("Audi");
    return veh;
  }

  @Bean(value = "hondaVehicle")
  Vehicle createVehicle2() {
    var veh = new Vehicle();
    veh.setName("Honda");
    return veh;
  }

  //@Primary: tells Spring which bean to prefer when multiple beans of the same type exist.
  @Primary
  @Bean("ferrariVehicle")
  Vehicle createVehicle3() {
    var veh = new Vehicle();
    veh.setName("Ferrari");
    return veh;
  }

}
