package com.hwms.asgard;

import org.hzero.autoconfigure.EnableHZeroAsgard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableHZeroAsgard
public class HwmsAsgardApplication {

    public static void main(String[] args){
        SpringApplication.run(HwmsAsgardApplication.class,args);
    }
}
