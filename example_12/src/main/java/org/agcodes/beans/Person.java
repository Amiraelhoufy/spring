package org.agcodes.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Person {

  private String name="Lucy";
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

  /*
   2. Setter Injection:
      - Optional dependencies or when you need to change them later.
   */
  @Autowired
  public void setVehicle(Vehicle vehicle) {
    this.vehicle = vehicle;
  }
}
