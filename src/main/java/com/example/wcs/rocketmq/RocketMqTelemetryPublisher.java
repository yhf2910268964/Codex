package com.example.wcs.rocketmq;

import com.example.wcs.application.TelemetryPublisher;
import com.example.wcs.domain.WcsMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "wcs.telemetry.mode", havingValue = "rocketmq", matchIfMissing = true)
public class RocketMqTelemetryPublisher implements TelemetryPublisher {

    private final RocketMQTemplate rocketMQTemplate;
    private final RocketMqTelemetryProperties properties;

    @Override
    public void publish(WcsMessage message) {
        if (!properties.isEnabled()) {
            log.debug("Skip RocketMQ telemetry publish because it is disabled: {}", message.messageId());
            return;
        }

        rocketMQTemplate.syncSend(properties.destination(), message);
        log.info("[RocketMQ] published messageId={}, destination={}", message.messageId(), properties.destination());
    }
}
