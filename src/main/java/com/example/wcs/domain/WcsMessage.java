package com.example.wcs.domain;

import java.time.Instant;
import java.util.Map;

/**
 * WCS 内部统一消息模型，用于在 REST、TCP、MQTT 等边界之间传递指令上下文。
 *
 * @param messageId 系统生成的唯一消息 ID，用于日志关联和调用方追踪
 * @param deviceId 目标设备或点位标识
 * @param protocol 消息应路由到的通讯协议
 * @param command 设备侧命令编码或命令名称
 * @param payload 命令业务参数
 * @param timestamp 消息进入系统的时间戳
 */
public record WcsMessage(
        String messageId,
        String deviceId,
        ProtocolType protocol,
        String command,
        Map<String, Object> payload,
        Instant timestamp
) {
}
