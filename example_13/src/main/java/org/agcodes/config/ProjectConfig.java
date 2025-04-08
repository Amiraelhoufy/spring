package org.agcodes.config;

import org.agcodes.beans.Person;
import org.agcodes.beans.Vehicle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

// @Configuration: Mark this class as source of bean definitions for the application context
// @ComponentScan: tells Spring to look for classes  with @Component in the specified packages and register them as beans
@Configuration
@ComponentScan(basePackages ={"org.agcodes.beans", "org.agcodes.implementation","org.agcodes.services"})
public class ProjectConfig {

}
