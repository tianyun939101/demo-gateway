package com.hwms.register;


import org.hzero.autoconfigure.register.EnableHZeroRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableHZeroRegister
public class HwmsRegisterApplication {

    public static void main(String[] args){
        SpringApplication.run(HwmsRegisterApplication.class,args);
    }
}
