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
 * WCS 通讯入口控制器。
 * <p>
 * 该接口通常由上层系统（WMS/MES/调度中心）调用，用于下发设备控制命令。
 */
@RestController
@RequestMapping("/api/v1/communications")
@RequiredArgsConstructor
public class CommunicationController {

    private final CommunicationService communicationService;

    /**
     * 接收并受理指令发送请求。
     *
     * @param request 请求体（含设备、协议、命令、参数）
     * @return 202 Accepted + messageId（表示已受理，不代表设备执行完成）
     */
    @PostMapping("/commands")
    public ResponseEntity<Map<String, String>> sendCommand(@Valid @RequestBody SendCommandRequest request) {
        // 在边界层生成全局唯一消息 ID，便于后续全链路追踪。
        String messageId = UUID.randomUUID().toString();

        // 统一封装消息模型，隔离上游 DTO 与领域对象。
        WcsMessage message = new WcsMessage(
                messageId,
                request.deviceId(),
                request.protocol(),
                request.command(),
                request.payload(),
                Instant.now()
        );

        communicationService.sendCommand(message);

        // 采用异步受理语义，符合工业通信“已接收/处理中”常见模式。
        return ResponseEntity.accepted().body(Map.of("messageId", messageId));
    }
}
