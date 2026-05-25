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

@RestController
@RequestMapping("/api/v1/communications")
@RequiredArgsConstructor
public class CommunicationController {

    private final CommunicationService communicationService;

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
