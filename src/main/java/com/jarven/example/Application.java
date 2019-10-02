package com.jarven.example;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description:
 * @author: 何佳文
 * @date: 2019/10/2 9:09 下午
 */
@SpringBootApplication(scanBasePackages = {"com.jarven.example"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
