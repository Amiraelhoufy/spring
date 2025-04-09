package org.agcodes.implementation;

import org.agcodes.interfaces.Tyres;
import org.springframework.stereotype.Component;

@Component
public class BridgeStoneTyres implements Tyres {

  @Override
  public String rotate() {
    return "rotating the 'BridgeStone' Tyres";

  }

  @Override
  public String stop() {
    return "Vehicle stopped with the help of 'BridgeStone' Tyres";
  }
}
