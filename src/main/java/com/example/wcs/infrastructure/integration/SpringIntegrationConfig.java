package com.example.wcs.infrastructure.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class SpringIntegrationConfig {

    @Bean
    public MessageChannel wcsOutboundChannel() {
        return new PublishSubscribeChannel();
    }
}
