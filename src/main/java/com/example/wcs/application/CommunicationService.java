package com.example.wcs.application;

import com.example.wcs.domain.WcsMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * WCS 通讯应用服务。
 * <p>
 * 职责：
 * 1) 对外暴露统一的“指令发送”用例，屏蔽底层协议差异；
 * 2) 负责组织消息分发与遥测发布两个动作；
 * 3) 作为后续扩展点，可在此处加入幂等、审计、重试、超时、链路追踪等横切逻辑。
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CommunicationService {

    /**
     * 指令分发抽象（可由 TCP、MQ、PLC 网关等实现）。
     */
    private final MessageDispatcher messageDispatcher;

    /**
     * 遥测发布抽象（可由 MQTT/Kafka/AMQP 等实现）。
     */
    private final TelemetryPublisher telemetryPublisher;

    /**
     * 发送 WCS 指令。
     * <p>
     * 当前行为：先分发指令，再发布遥测事件。
     * 生产建议：
     * - 若对一致性要求高，可考虑 Outbox/事务消息；
     * - 若对可用性优先，可配置失败降级策略（如仅记录失败并异步补偿）。
     *
     * @param message 统一消息模型
     */
    public void sendCommand(WcsMessage message) {
        log.debug("Start sendCommand, messageId={}, protocol={}, deviceId={}",
                message.messageId(), message.protocol(), message.deviceId());

        // Step-1: 执行协议分发（下发命令至设备/网关）
        messageDispatcher.dispatch(message);

        // Step-2: 发布遥测（用于监控、追踪、审计或订阅消费）
        telemetryPublisher.publish(message);

        log.debug("Finished sendCommand, messageId={}", message.messageId());
    }
}
