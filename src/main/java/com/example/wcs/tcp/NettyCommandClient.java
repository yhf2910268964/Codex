package com.example.wcs.tcp;

import com.example.wcs.domain.WcsMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Slf4j
@Component
public class NettyCommandClient {

    private final NettyClientProperties properties;
    private final ObjectMapper objectMapper;
    private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup(1);
    private volatile Channel channel;

    public NettyCommandClient(NettyClientProperties properties, ObjectMapper objectMapper) {
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    public void send(WcsMessage message) {
        try {
            Channel activeChannel = ensureConnected();
            activeChannel.writeAndFlush(objectMapper.writeValueAsString(message) + System.lineSeparator()).sync();
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while sending TCP command through Netty", ex);
        } catch (JsonProcessingException ex) {
            throw new IllegalArgumentException("Failed to serialize WCS message for Netty transport", ex);
        }
    }

    private Channel ensureConnected() throws InterruptedException {
        Channel existing = channel;
        if (existing != null && existing.isActive()) {
            return existing;
        }

        synchronized (this) {
            if (channel != null && channel.isActive()) {
                return channel;
            }

            Bootstrap bootstrap = new Bootstrap()
                    .group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectTimeoutMillis())
                    .handler(new StringEncoder(StandardCharsets.UTF_8));

            ChannelFuture future = bootstrap.connect(properties.getHost(), properties.getPort()).sync();
            channel = future.channel();
            log.info("Connected Netty command client to {}:{}", properties.getHost(), properties.getPort());
            return channel;
        }
    }

    @PreDestroy
    public void close() {
        Channel activeChannel = channel;
        if (activeChannel != null) {
            activeChannel.close();
        }
        eventLoopGroup.shutdownGracefully();
    }
}
