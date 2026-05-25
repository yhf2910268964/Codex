package com.example.wcs.infrastructure.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

/**
 * Spring Integration 基础配置，集中声明通讯模块使用的消息通道。
 */
@Configuration
public class SpringIntegrationConfig {

    /**
     * 出站消息广播通道，适合后续挂载多个协议处理器或审计订阅者。
     *
     * @return WCS 出站消息通道
     */
    @Bean
    public MessageChannel wcsOutboundChannel() {
        return new PublishSubscribeChannel();
    }
}
