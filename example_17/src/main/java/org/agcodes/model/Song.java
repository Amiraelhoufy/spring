package org.agcodes.model;

import org.springframework.stereotype.Component;

// Not a component/bean as it only Holds data
public class Song {

  private String title;
  private String singerName;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSingerName() {
    return singerName;
  }

  public void setSingerName(String singerName) {
    this.singerName = singerName;
  }
}
