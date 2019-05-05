package com.hwms.swagger;

import org.hzero.autoconfigure.swagger.EnableHZeroSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableHZeroSwagger
public class HwmsSwagger {

    public static void main(String[]  args){
        SpringApplication.run(HwmsSwagger.class,args);
    }
}
