package com.hwms.config;


import org.hzero.autoconfigure.config.EnableHZeroConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableHZeroConfig
public class HwmsConfigApplication {

    public  static void main(String[] args){
        SpringApplication.run(HwmsConfigApplication.class,args);
    }
}
