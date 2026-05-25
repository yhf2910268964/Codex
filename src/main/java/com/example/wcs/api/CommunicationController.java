package com.example.wcs.api;

import com.example.wcs.application.CommunicationService;
import com.example.wcs.domain.WcsMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

/**
 * 对外暴露 WCS 通讯指令接口，将 REST 请求转换为内部统一消息模型。
 */
@RestController
@RequestMapping("/api/v1/communications")
@RequiredArgsConstructor
public class CommunicationController {

    private final CommunicationService communicationService;

    /**
     * 接收上层系统下发的设备指令，并异步交由通讯服务处理。
     *
     * @param request 指令请求体，包含目标设备、通讯协议、命令和业务载荷
     * @return 已受理响应，返回系统生成的消息 ID 便于调用方追踪
     */
    @PostMapping("/commands")
    public ResponseEntity<Map<String, String>> sendCommand(@Valid @RequestBody SendCommandRequest request) {
        WcsMessage message = new WcsMessage(
                UUID.randomUUID().toString(),
                request.deviceId(),
                request.protocol(),
                request.command(),
                request.payload(),
                Instant.now()
        );

        communicationService.sendCommand(message);
        return ResponseEntity.accepted().body(Map.of("messageId", message.messageId()));
    }
}
