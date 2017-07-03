package cn.demo.service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "cn.demo.service1")
@EnableFeignClients//1负载均衡
@ComponentScan
@EnableEurekaClient//注册服务
@EnableCircuitBreaker////2断路器
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
