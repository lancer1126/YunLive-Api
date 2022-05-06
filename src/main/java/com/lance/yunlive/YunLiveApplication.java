package com.lance.yunlive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class YunLiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(YunLiveApplication.class, args);
    }

}
