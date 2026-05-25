# WCS Communication Module (Java 21 + Spring Boot)

一个面向仓储场景（WCS）的通讯功能模块骨架，提供统一消息模型与多协议扩展点。

## 技术栈
- Java 21
- Spring Boot 3.5.x
- Spring Integration（IP/MQTT）
- REST API（接收上层系统下发命令）

## 模块说明
- `api`: HTTP 接口层，处理入参校验与请求编排。
- `application`: 应用服务层，组织分发与发布流程。
- `domain`: 领域模型层，统一消息结构与协议枚举。
- `tcp`: TCP 协议分发适配器（示例实现）。
- `mqtt`: MQTT 遥测发布适配器（示例实现）。
- `infrastructure/integration`: Spring Integration 通道配置。

## API 示例
### 下发命令
`POST /api/v1/communications/commands`

```json
{
  "deviceId": "stacker-01",
  "protocol": "TCP",
  "command": "MOVE",
  "payload": {
    "from": "A01",
    "to": "B09",
    "priority": 5
  }
}
```

响应（202）：
```json
{
  "messageId": "f8f8c5d9-xxxx-xxxx-xxxx-2d0679a7f3d1"
}
```

## 生产落地建议
1. 在 `TcpMessageDispatcher` 中接入 Netty 或 Spring Integration TCP gateway。
2. 在 `MqttTelemetryPublisher` 中接入 MQTT Broker（EMQX/Mosquitto）。
3. 引入消息幂等（基于 `messageId`）与重试补偿机制。
4. 增加链路追踪（traceId/spanId）与告警指标（成功率、延迟、失败原因）。
