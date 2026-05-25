package com.example.wcs.tcp;

import com.example.wcs.application.MessageDispatcher;
import com.example.wcs.domain.ProtocolType;
import com.example.wcs.domain.WcsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TcpMessageDispatcher implements MessageDispatcher {

    @Override
    public void dispatch(WcsMessage message) {
        if (message.protocol() != ProtocolType.TCP) {
            log.debug("Skip non-TCP message: {}", message.messageId());
            return;
        }

        // 生产环境中可替换为 Netty/Spring Integration TCP outbound gateway
        log.info("[TCP] dispatch command={}, deviceId={}, payload={}",
                message.command(), message.deviceId(), message.payload());
    }
}
