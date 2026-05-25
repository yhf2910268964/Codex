package com.example.wcs.tcp;

import com.example.wcs.application.MessageDispatcher;
import com.example.wcs.domain.ProtocolType;
import com.example.wcs.domain.WcsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * TCP 指令分发适配器，仅处理协议类型为 TCP 的 WCS 消息。
 */
@Slf4j
@Component
public class TcpMessageDispatcher implements MessageDispatcher {

    /**
     * 过滤非 TCP 消息，并将 TCP 指令发送到设备通讯层。
     *
     * <p>当前实现以日志模拟真实发送，生产环境可替换为 Netty 或
     * Spring Integration TCP outbound gateway。</p>
     *
     * @param message 待分发的 WCS 指令消息
     */
    @Override
    public void dispatch(WcsMessage message) {
        if (message.protocol() != ProtocolType.TCP) {
            log.debug("Skip non-TCP message: {}", message.messageId());
            return;
        }

        log.info("[TCP] dispatch command={}, deviceId={}, payload={}",
                message.command(), message.deviceId(), message.payload());
    }
}
