package org.agcodes.eazyschool.aspects;

import java.time.Duration;
import java.time.Instant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

  // Return object of joinPoint So AOP won't interfere with the method’s behavior.
  @Around("execution(* org.agcodes.eazyschool..*.*(..))")
  public Object log(ProceedingJoinPoint joinPoint) throws Throwable{
    // before Method execution
    log.info(joinPoint.getSignature().toString() + " method execution started");
    Instant start = Instant.now();
    // continue to execute the actual method
    Object result = joinPoint.proceed();
    // After Method execution
    Instant finish = Instant.now();
    long timeElapsed = Duration.between(start,finish).toMillis();
    log.info("Time taken to execute " + joinPoint.getSignature().toString() +" method is : "+ timeElapsed);
    log.info(joinPoint.getSignature().toString() + "method execution ended");

    return result;
  }

  @AfterThrowing(value = "execution(* org.agcodes.eazyschool.*.*(..))",throwing = "ex")
  public void logException(JoinPoint joinPoint, Exception ex) {
    log.error(joinPoint.getSignature().toString() + " An exception happened due to" + ex.getMessage());
  }

}
