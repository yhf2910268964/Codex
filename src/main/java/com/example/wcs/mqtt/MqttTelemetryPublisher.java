package com.example.wcs.mqtt;

import com.example.wcs.application.TelemetryPublisher;
import com.example.wcs.domain.WcsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * MQTT 遥测发布器。
 * <p>
 * 当前版本为示例实现（日志打印）。
 * 生产环境可替换为：
 * - Spring Integration MQTT outbound adapter
 * - Eclipse Paho / HiveMQ Client
 */
@Slf4j
@Component
public class MqttTelemetryPublisher implements TelemetryPublisher {

    @Override
    public void publish(WcsMessage message) {
        // TODO: 构造 topic（例如 wcs/{deviceId}/telemetry）和 QoS，并发布至 Broker。
        log.info("[MQTT] publish messageId={}, protocol={}, payload={}",
                message.messageId(), message.protocol(), message.payload());
    }
}
