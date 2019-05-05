package com.demo.gateway;


import org.hzero.autoconfigure.gateway.EnableHZeroGateway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableHZeroGateway
public class DemoGateWayApplication {

     public  static void  main(String[] args){
         SpringApplication.run(DemoGateWayApplication.class,args);
     }
}
