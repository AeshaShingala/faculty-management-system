package com.goals.facultymanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class FacultyManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacultyManagementSystemApplication.class, args);
    }

}
