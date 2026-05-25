package com.example.wcs.mqtt;

import com.example.wcs.application.TelemetryPublisher;
import com.example.wcs.domain.WcsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * MQTT 消息发布适配器，用于把 WCS 消息同步到外部 MQTT broker。
 */
@Slf4j
@Component
public class MqttTelemetryPublisher implements TelemetryPublisher {

    /**
     * 发布 WCS 消息到 MQTT 通道。
     *
     * <p>当前实现以日志模拟真实发布，生产环境可对接 EMQX、Mosquitto
     * 或其他 MQTT broker。</p>
     *
     * @param message 待发布的 WCS 消息
     */
    @Override
    public void publish(WcsMessage message) {
        log.info("[MQTT] publish messageId={}, protocol={}, payload={}",
                message.messageId(), message.protocol(), message.payload());
    }
}
