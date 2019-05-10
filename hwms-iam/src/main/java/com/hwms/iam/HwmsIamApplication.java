package com.hwms.iam;

import org.hzero.autoconfigure.iam.EnableHZeroIam;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableHZeroIam
public class HwmsIamApplication {

    public static void main(String[] args){
        SpringApplication.run(HwmsIamApplication.class,args);
    }

}
