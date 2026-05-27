package com.example.wcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WcsCommunicationApplication {

    /**
     * WCS 通讯能力模块启动入口。
     */
    public static void main(String[] args) {
        SpringApplication.run(WcsCommunicationApplication.class, args);
    }
}
