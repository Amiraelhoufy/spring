package org.agcodes;

import java.util.Random;
import java.util.function.Supplier;
import org.agcodes.beans.Vehicle;
import org.agcodes.config.ProjectConfig;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

public class Example7 {

  public static void main(String[] args) {

    var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

    Vehicle volkswagen = new Vehicle();
    volkswagen.setName("volkswagen");
    Supplier<Vehicle> volkswagenSupplier = ()-> volkswagen;

    Supplier<Vehicle> audiSupplier = ()->{
      Vehicle audi = new Vehicle();
      audi.setName("audi");
      return audi;
    };

    Random random = new Random();
    int randomNumber = random.nextInt(10);

    System.out.println("Random Number= " + randomNumber);
    if(randomNumber % 2 == 0) {
      context.registerBean("volkswagen",Vehicle.class, volkswagenSupplier);
    }else{
      context.registerBean("audi",Vehicle.class,audiSupplier);
    }
    Vehicle audiVehicle = null;
    Vehicle volksVehicle = null;


    try{
      volksVehicle = context.getBean("volkswagen",Vehicle.class);
    }catch(NoSuchBeanDefinitionException e){
      System.out.println("Error while creating volkswagen vehicle");
    }
    try{
      audiVehicle = context.getBean("audi",Vehicle.class);
    }catch(NoSuchBeanDefinitionException e){
      System.out.println("Error while creating audi vehicle");
  }

    if(volksVehicle != null){
      System.out.println("Programming Vehicle name from spring context is: " +volksVehicle.getName());
    }else{
      System.out.println("Programming Vehicle name from spring context is: " +audiVehicle.getName());
    }
  }
}