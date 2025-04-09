package org.agcodes.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("personBean")
public class Person {

  private String name = "Lucy";
  private final Vehicle vehicle;

  /*
   3. Constructor Injection:
    -> Best practice if you're looking for final field for security reasons.
   */
  @Autowired
  public Person(Vehicle vehicle) {
//    System.out.println("Person bean is created by Spring");
    this.vehicle = vehicle;
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

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        ", vehicle=" + vehicle +
        '}';
  }
}
