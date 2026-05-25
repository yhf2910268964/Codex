package com.example.wcs.application;

import com.example.wcs.domain.WcsMessage;

/**
 * 指令下发端口，由具体通讯协议适配器实现。
 */
public interface MessageDispatcher {

    /**
     * 将统一消息模型分发到对应协议的设备通道。
     *
     * @param message 待下发的 WCS 指令消息
     */
    void dispatch(WcsMessage message);
}
