package com.hwms.oauth;

import org.hzero.autoconfigure.oauth.EnableHZeroOauth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EnableHZeroOauth
public class HwmsOauthApplication {

    public static void main(String[] args){
        SpringApplication.run(HwmsOauthApplication.class,args);
    }
}
