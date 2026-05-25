package com.example.wcs.tcp;

import com.example.wcs.application.MessageDispatcher;
import com.example.wcs.domain.ProtocolType;
import com.example.wcs.domain.WcsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * TCP 指令分发器。
 * <p>
 * 当前版本为示例实现（日志打印）。
 * 生产环境可替换为：
 * - Spring Integration TCP outbound gateway
 * - Netty 客户端（长连接、心跳、重连、背压控制）
 */
@Slf4j
@Component
public class TcpMessageDispatcher implements MessageDispatcher {

    @Override
    public void dispatch(WcsMessage message) {
        // 协议守卫：仅处理 TCP 协议消息，避免非本协议消息误投。
        if (message.protocol() != ProtocolType.TCP) {
            log.debug("Skip non-TCP message: {}", message.messageId());
            return;
        }

        // TODO: 在此处执行协议编码（如报文头、校验位、序列号等）并写入 TCP 通道。
        log.info("[TCP] dispatch command={}, deviceId={}, payload={}",
                message.command(), message.deviceId(), message.payload());
    }
}
