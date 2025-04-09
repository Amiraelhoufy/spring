package org.agcodes.implementation;

import org.agcodes.interfaces.Speakers;
import org.springframework.stereotype.Component;

@Component
public class SonySpeakers implements Speakers {

  @Override
  public String makeSound() {
    return "Playing musics with 'Sony' Speakers";

  }
}
