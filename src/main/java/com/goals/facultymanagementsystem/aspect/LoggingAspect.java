package com.goals.facultymanagementsystem.aspect;

import com.goals.facultymanagementsystem.dto.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class LoggingAspect {
    @Pointcut(value = "execution(* com.goals.facultymanagementsystem.controller.FacultyController.*(..))")
    public void loggingPointCut() {
    }

    @Before(value = "loggingPointCut()")
    public void before(JoinPoint joinPoint) {
        log.info("Before executing the method: " + joinPoint.getSignature());
    }

    @AfterReturning(value = "loggingPointCut()", returning = "responseEntity")
    public void afterReturning(ResponseEntity<GenericResponse> responseEntity) {
        log.info("After method invocation: " + responseEntity);
    }

    @AfterThrowing(value = "loggingPointCut()", throwing = "e")
    public void afterThrowing(Exception e) {
        log.info("Error message after method invocation: " + e.getMessage());
    }
}


