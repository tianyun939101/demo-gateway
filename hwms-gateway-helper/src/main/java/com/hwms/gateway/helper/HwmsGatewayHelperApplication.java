package com.hwms.gateway.helper;

import org.hzero.autoconfigure.gateway.helper.EnableHZeroGatewayHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableHZeroGatewayHelper
public class HwmsGatewayHelperApplication {

    public static void main(String[] args){
        SpringApplication.run(HwmsGatewayHelperApplication.class,args);
    }
}
