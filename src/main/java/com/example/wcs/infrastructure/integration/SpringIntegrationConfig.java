package com.example.wcs.infrastructure.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

/**
 * Spring Integration 基础配置。
 * <p>
 * 这里定义最小可用的出站通道，后续可继续扩展为：
 * - TCP outbound flow
 * - MQTT outbound flow
 * - 错误通道 / 重试流 / 死信流
 */
@Configuration
public class SpringIntegrationConfig {

    /**
     * WCS 出站总线（发布-订阅模型）。
     * 多个订阅者可并行消费同一条消息，例如：
     * 1) 协议下发处理器
     * 2) 遥测采集处理器
     * 3) 审计日志处理器
     */
    @Bean
    public MessageChannel wcsOutboundChannel() {
        return new PublishSubscribeChannel();
    }
}
