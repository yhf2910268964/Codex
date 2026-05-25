package com.example.wcs.application;

import com.example.wcs.domain.WcsMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunicationService {

    private final MessageDispatcher messageDispatcher;
    private final TelemetryPublisher telemetryPublisher;

    public void sendCommand(WcsMessage message) {
        messageDispatcher.dispatch(message);
        telemetryPublisher.publish(message);
    }
}
