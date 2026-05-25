package com.example.wcs.domain;

import java.time.Instant;
import java.util.Map;

/**
 * WCS 统一消息模型。
 *
 * @param messageId 全局消息 ID（用于幂等、追踪、审计）
 * @param deviceId  目标设备标识（如堆垛机、输送线、RGV）
 * @param protocol  通讯协议类型
 * @param command   业务命令（如 MOVE/STOP/RESET）
 * @param payload   命令参数或遥测载荷（键值结构，便于扩展）
 * @param timestamp 消息创建时间（UTC）
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
