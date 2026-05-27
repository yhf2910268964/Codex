package com.example.wcs.rocketmq;

import com.example.wcs.domain.ProtocolType;
import com.example.wcs.domain.WcsMessage;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class RocketMqTelemetryPublisherTest {

    private final RocketMQTemplate rocketMQTemplate = mock(RocketMQTemplate.class);

    @Test
    void publishesTelemetryToConfiguredTopicAndTag() {
        RocketMqTelemetryProperties properties = new RocketMqTelemetryProperties();
        properties.setTopic("wcs-telemetry");
        properties.setTag("command");
        properties.setEnabled(true);
        RocketMqTelemetryPublisher publisher = new RocketMqTelemetryPublisher(rocketMQTemplate, properties);
        WcsMessage message = message();

        publisher.publish(message);

        verify(rocketMQTemplate).syncSend("wcs-telemetry:command", message);
    }

    @Test
    void skipsPublishWhenDisabled() {
        RocketMqTelemetryProperties properties = new RocketMqTelemetryProperties();
        properties.setEnabled(false);
        RocketMqTelemetryPublisher publisher = new RocketMqTelemetryPublisher(rocketMQTemplate, properties);
        WcsMessage message = message();

        publisher.publish(message);

        verify(rocketMQTemplate, never()).syncSend("wcs-telemetry:command", message);
    }

    private static WcsMessage message() {
        return new WcsMessage(
                "message-1",
                "device-1",
                ProtocolType.TCP,
                "MOVE",
                Map.of("x", 1),
                Instant.parse("2026-05-27T00:00:00Z")
        );
    }
}
