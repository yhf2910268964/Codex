package com.example.wcs.mqtt;

import com.example.wcs.application.TelemetryPublisher;
import com.example.wcs.domain.WcsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttTelemetryPublisher implements TelemetryPublisher {

    @Override
    public void publish(WcsMessage message) {
        // 生产环境中可对接 MQTT broker（EMQX/Mosquitto）
        log.info("[MQTT] publish messageId={}, protocol={}, payload={}",
                message.messageId(), message.protocol(), message.payload());
    }
}
