package com.vb_code.FlixPay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FlixPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlixPayApplication.class, args);
    }

}
