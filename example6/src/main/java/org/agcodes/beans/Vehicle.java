package org.agcodes.beans;

import jakarta.annotation.PostConstruct;
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

  // @PostConstruct runs a method automatically after the Spring bean is fully initialized.
  @PostConstruct
  public void initialize(){
    this.name = "Honda";
  }

  public void printHello(){
    System.out.println(
        "Printing Hello from Component Vehicle Bean");
  }
}
