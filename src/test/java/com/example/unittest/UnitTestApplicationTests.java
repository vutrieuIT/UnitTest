package com.example.unittest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
class UnitTestApplicationTests {

    public static void main(String[] args) {
        SpringApplication.run(UnitTestApplication.class, args);
    }
}
