package com.scartoon.starcartoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class StarCartoonApplication {

    public static void main(String[] args) {

        SpringApplication.run(StarCartoonApplication.class, args);
    }


}
