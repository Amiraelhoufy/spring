package org.agcodes.aspects;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LoggerAspect {

  private Logger logger = Logger.getLogger(LoggerAspect.class.getName());

  // Return object of joinPoint So AOP won't interfere with the method’s behavior.
  @Around("execution(* org.agcodes.services.*.*(..))")
  public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
    // before Method execution
    logger.info(joinPoint.getSignature().toString() + " method execution started");
    Instant start = Instant.now();

    // continue to execute the actual method
    Object result = joinPoint.proceed();

    // After Method execution
    Instant finish = Instant.now();
    long timeElapsed = Duration.between(start,finish).toMillis();
    logger.info("Time taken to execute the method: " + timeElapsed);
    logger.info(joinPoint.getSignature().toString() + "method execution ended");

    return result;
  }

  @Around("@Annotation(org.agcodes.interfaces.LogAspect)")
  public Object logWithAnnotation(ProceedingJoinPoint joinPoint) throws Throwable{
    // before Method execution
    logger.info(joinPoint.getSignature().toString() + " method execution started");
    Instant start = Instant.now();

    // continue to execute the actual method
    Object result = joinPoint.proceed();

    // After Method execution
    Instant finish = Instant.now();
    long timeElapsed = Duration.between(start,finish).toMillis();
    logger.info("Time taken to execute the method: " + timeElapsed);
    logger.info(joinPoint.getSignature().toString() + "method execution ended");

    return result;
  }
  @AfterThrowing(value = "execution(* org.agcodes.services.*.*(..))",throwing = "ex")
  public void logException(JoinPoint joinPoint, Exception ex) {
    logger.log(Level.SEVERE, joinPoint.getSignature().toString()
        + " An exception thrown with the help of"
        + " @AfterThrowing which happened due to" + ex.getMessage());
  }
  @AfterReturning(value = "execution(* org.agcodes.services.*.*(..))",returning = "retVal")
  public void logStatus(JoinPoint joinPoint, Object retVal){

    logger.log(Level.INFO, joinPoint.getSignature().toString()
        + " Logged by @AfterReturning"
        + " Method successfully processed with status" + retVal.toString());
  }

}
