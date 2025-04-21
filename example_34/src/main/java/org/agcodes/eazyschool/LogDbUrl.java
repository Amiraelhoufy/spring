package org.agcodes.eazyschool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class LogDbUrl implements CommandLineRunner {

  @Autowired
  private Environment env;

  @Override
  public void run(String... args) {
    System.out.println("Datasource URL: " + env.getProperty("spring.datasource.url"));
  }
}

