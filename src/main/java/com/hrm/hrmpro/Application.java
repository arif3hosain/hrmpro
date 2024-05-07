package com.hrm.hrmpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("===================================");
        System.out.println("            App Started            ");
        System.out.println("===================================");
    }

}
