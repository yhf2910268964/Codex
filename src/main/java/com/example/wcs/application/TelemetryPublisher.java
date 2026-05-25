package com.example.wcs.application;

import com.example.wcs.domain.WcsMessage;

/**
 * 遥测或指令回执发布端口，用于将通讯过程中的消息同步到外部消息系统。
 */
public interface TelemetryPublisher {

    /**
     * 发布消息状态、指令内容或后续扩展的设备遥测数据。
     *
     * @param message 待发布的 WCS 消息
     */
    void publish(WcsMessage message);
}
