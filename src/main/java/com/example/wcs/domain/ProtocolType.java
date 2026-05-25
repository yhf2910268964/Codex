package com.example.wcs.domain;

/**
 * WCS 通讯模块当前支持的协议类型。
 */
public enum ProtocolType {
    /**
     * 面向设备侧长连接或网关连接的 TCP 通讯。
     */
    TCP,

    /**
     * 面向消息代理的 MQTT 发布/订阅通讯。
     */
    MQTT,

    /**
     * 面向 HTTP 服务的 REST 通讯。
     */
    REST
}
