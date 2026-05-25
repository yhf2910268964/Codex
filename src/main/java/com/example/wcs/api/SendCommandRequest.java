package com.example.wcs.api;

import com.example.wcs.domain.ProtocolType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record SendCommandRequest(
        @NotBlank String deviceId,
        @NotNull ProtocolType protocol,
        @NotBlank String command,
        Map<String, Object> payload
) {
}
