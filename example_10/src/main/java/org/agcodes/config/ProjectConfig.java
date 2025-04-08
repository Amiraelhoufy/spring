package org.agcodes.config;

import org.agcodes.beans.Person;
import org.agcodes.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

// @Configuration: Mark this class as source of bean definitions for the application context
// @ComponentScan: tells Spring to look for classes  with @Component in the specified packages and register them as beans
@Configuration
@ComponentScan(basePackages = "org.agcodes.beans")
public class ProjectConfig {


  // Spring creates only 1 vehicle bean
@Bean
  public Vehicle createVehicle(){
  Vehicle vehicle = new Vehicle();
  vehicle.setName("Toyota");
  return vehicle;
}

@Bean
  public Person createPerson(Vehicle vehicle){
  Person person = new Person();
  person.setName("Lucy");

  /* Wiring Beans using "Method Parameter" */

  person.setVehicle(vehicle);
  return person;
}


}
