package com.example.wcs.application;

import com.example.wcs.domain.WcsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 通讯应用服务，负责编排指令下发和消息发布两个应用端口。
 */
@Service
@RequiredArgsConstructor
public class CommunicationService {

    private final MessageDispatcher messageDispatcher;
    private final TelemetryPublisher telemetryPublisher;

    /**
     * 处理上层系统下发的指令：先进入协议分发，再发布消息用于追踪或集成。
     *
     * @param message 已标准化的 WCS 指令消息
     */
    public void sendCommand(WcsMessage message) {
        messageDispatcher.dispatch(message);
        telemetryPublisher.publish(message);
    }
}
