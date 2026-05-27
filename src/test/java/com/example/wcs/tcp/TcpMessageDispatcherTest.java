package com.example.wcs.tcp;

import com.example.wcs.domain.ProtocolType;
import com.example.wcs.domain.WcsMessage;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class TcpMessageDispatcherTest {

    private final NettyCommandClient nettyCommandClient = mock(NettyCommandClient.class);
    private final TcpMessageDispatcher dispatcher = new TcpMessageDispatcher(nettyCommandClient);

    @Test
    void dispatchesTcpMessagesThroughNetty() {
        WcsMessage message = message(ProtocolType.TCP);

        dispatcher.dispatch(message);

        verify(nettyCommandClient).send(message);
    }

    @Test
    void ignoresNonTcpMessages() {
        WcsMessage message = message(ProtocolType.REST);

        dispatcher.dispatch(message);

        verify(nettyCommandClient, never()).send(message);
    }

    private static WcsMessage message(ProtocolType protocol) {
        return new WcsMessage(
                "message-1",
                "device-1",
                protocol,
                "MOVE",
                Map.of("x", 1),
                Instant.parse("2026-05-27T00:00:00Z")
        );
    }
}
