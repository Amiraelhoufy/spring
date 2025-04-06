package org.agcodes.beans;

import org.springframework.stereotype.Component;

//@Component: Tells Spring that this POJO is a bean
@Component
public class Vehicle {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void printHello(){
    System.out.println(
        "Printing Hello from Component Vehicle Bean");
  }
}
