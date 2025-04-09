package org.agcodes.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// @Configuration: Mark this class as source of bean definitions for the application context
// @ComponentScan: tells Spring to look for classes  with @Component in the specified packages and register them as beans
@Configuration
@ComponentScan(basePackages ={"org.agcodes.implementation",
    "org.agcodes.services","org.agcodes.aspects"})
@EnableAspectJAutoProxy
public class ProjectConfig {

}
