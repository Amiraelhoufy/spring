package org.agcodes.beans;

import org.agcodes.services.VehicleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("vehicleBean")
public class Vehicle {

  private String name = "Toyota";
  private final VehicleServices vehicleServices;

  @Autowired
  public Vehicle(VehicleServices vehicleServices) {
    System.out.println("Vehicle is created by Spring");
    this.vehicleServices = vehicleServices;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public VehicleServices getVehicleServices() {
    return vehicleServices;
  }

  public void printHello(){
    System.out.println(
        "Printing Hello from Component Vehicle Bean");
  }

  @Override
  public String toString() {
    return "Vehicle{" +
        "name='" + name + '\'' +
        ", vehicleServices=" + vehicleServices +
        '}';
  }
}
