package com.hwms.platform;


import org.hzero.autoconfigure.platform.EnableHZeroPlatform;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableHZeroPlatform
@EnableEurekaClient
@SpringBootApplication
public class HwmsPlatformApplication {

    public  static void main(String[] args){
        SpringApplication.run(HwmsPlatformApplication.class,args);
    }
}
