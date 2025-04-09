package org.agcodes.implementation;

import org.agcodes.interfaces.Tyres;
import org.springframework.stereotype.Component;

@Component
public class MichelinTyres implements Tyres {

  @Override
  public String rotate() {
    return "rotating the 'Michelin' Tyres";

  }
}
