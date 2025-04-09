package org.agcodes.implementation;

import org.agcodes.interfaces.Tyres;
import org.springframework.stereotype.Component;

@Component
public class MichelinTyres implements Tyres {

  @Override
  public String rotate() {
    return "rotating the 'Michelin' Tyres";

  }

  @Override
  public String stop() {
    return "Vehicle stopped with the help of 'Michelin' Tyres";
  }

}
