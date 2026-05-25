package com.example.wcs.api;

import com.example.wcs.domain.ProtocolType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

/**
 * 上层系统下发设备指令时使用的 REST 请求模型。
 *
 * @param deviceId 目标设备或点位标识
 * @param protocol 本次指令期望使用的通讯协议
 * @param command 设备侧可识别的命令编码或命令名称
 * @param payload 命令附带的业务参数，允许为空
 */
public record SendCommandRequest(
        @NotBlank String deviceId,
        @NotNull ProtocolType protocol,
        @NotBlank String command,
        Map<String, Object> payload
) {
}
