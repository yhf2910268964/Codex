package com.example.wcs.application;

import com.example.wcs.domain.WcsMessage;

public interface TelemetryPublisher {

    void publish(WcsMessage message);
}
