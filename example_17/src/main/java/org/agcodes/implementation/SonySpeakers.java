package org.agcodes.implementation;

import org.agcodes.interfaces.Speakers;
import org.agcodes.model.Song;
import org.springframework.stereotype.Component;

@Component
public class SonySpeakers implements Speakers {

  @Override
  public String makeSound(Song song){
    return "Playing the song "+ song.getTitle()+ " by "
        + song.getSingerName()+
        " with Sony speakers";
  }
}
