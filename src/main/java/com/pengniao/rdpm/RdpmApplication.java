package com.pengniao.rdpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.pengniao.rdpm.demo.dao")
public class RdpmApplication {

    public static void main(String[] args) {
        SpringApplication.run(RdpmApplication.class, args);
    }
}
