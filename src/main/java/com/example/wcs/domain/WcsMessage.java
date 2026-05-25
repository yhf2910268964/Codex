package com.example.wcs.domain;

import java.time.Instant;
import java.util.Map;

public record WcsMessage(
        String messageId,
        String deviceId,
        ProtocolType protocol,
        String command,
        Map<String, Object> payload,
        Instant timestamp
) {
}
