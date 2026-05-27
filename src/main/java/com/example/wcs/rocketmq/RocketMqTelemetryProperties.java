package com.example.wcs.rocketmq;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "wcs.rocketmq.telemetry")
public class RocketMqTelemetryProperties {

    private boolean enabled = true;
    private String topic = "wcs-telemetry";
    private String tag = "command";

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String destination() {
        if (tag == null || tag.isBlank()) {
            return topic;
        }
        return topic + ":" + tag;
    }
}
