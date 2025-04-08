package org.agcodes.services;

import org.agcodes.interfaces.Speakers;
import org.agcodes.interfaces.Tyres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class VehicleServices {

private final Speakers speakers;
private final Tyres tyres;

  @Autowired
  public VehicleServices(@Qualifier("boseSpeakers") Speakers speakers, @Qualifier("michelinTyres") Tyres tyres) {
    this.speakers = speakers;
    this.tyres = tyres;
  }

  public void playMusic(){
    System.out.println("" + speakers.makeSound());
  }

  public void rotateTyres(){
    String rotation = tyres.rotate();
    System.out.println(rotation);
  }
  public Speakers getSpeakers() {
    return speakers;
  }

  public Tyres getTyres() {
    return tyres;
  }

  @Override
  public String toString() {
    return "VehicleServices{" +
        "speakers=" + speakers.makeSound() +
        ", tyres=" + tyres.rotate() +
        '}';
  }
}
