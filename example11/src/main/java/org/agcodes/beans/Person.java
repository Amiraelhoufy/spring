package org.agcodes.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {

  private String name="Lucy";
  /*
   1. Field Injection:
   ->  not recommended for production
   -> The field can't be Final -> Because Spring injects the dependency after the object is constructed
   */

  /*
  (required = false) to avoid "NoSuchBeanDefinitionException"
   */
  @Autowired(required = false)
  private Vehicle vehicle;

  public Person() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Vehicle getVehicle() {
    return vehicle;
  }

  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }
}
