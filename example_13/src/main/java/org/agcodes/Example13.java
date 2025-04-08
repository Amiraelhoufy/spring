package org.agcodes;

import org.agcodes.beans.Person;
import org.agcodes.beans.Vehicle;
import org.agcodes.config.ProjectConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Example13 {

  public static void main(String[] args) {

    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);
    String[] vehicleBeanName = context.getBeanNamesForType(Vehicle.class);
    String[] personBeanName = context.getBeanNamesForType(Person.class);

    System.out.println("Vehicle Bean Name: " + vehicleBeanName[0]);
    System.out.println("Person Bean Name: " + personBeanName[0]);

    Vehicle vehicle = context.getBean(Vehicle.class);
    Person person = context.getBean(Person.class);

    vehicle.getVehicleServices().playMusic();
    vehicle.getVehicleServices().rotateTyres();

    person.getVehicle().getVehicleServices().playMusic();
    person.getVehicle().getVehicleServices().rotateTyres();

//    System.out.println(vehicle.toString());
//    System.out.println(person.toString());

  }
}