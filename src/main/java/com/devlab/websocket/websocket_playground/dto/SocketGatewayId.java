package com.devlab.websocket.websocket_playground.dto;

import com.devlab.websocket.websocket_playground.service.SocketGatewayService;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Data
public class SocketGatewayId {

    private String socketCd;

    private String socketName;

    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public SocketGatewayId(String socketCd, String socketName) {
        this.socketCd = socketCd;
        this.socketName = socketName;
    }

    public void handleActions(WebSocketSession session, SocketGatewayData gatewayData,
                              SocketGatewayService socketGatewayService) {

        log.info("handleActions type={}, socketCd={}, socketName={}, message={}",
                gatewayData.getType(), gatewayData.getSocketCd(), gatewayData.getSocketName(),
                gatewayData.getMessage());

        if (gatewayData.getType() == SocketGatewayData.MessageType.FIRST_CONNECT) {
            sessions.add(session);
            log.info("add session={}", session);
            gatewayData.setMessage(gatewayData.getSocketName() + "님이 입장하였습니다.");
            sendData(gatewayData, socketGatewayService);
            return;
        }

        if (gatewayData.getType() == SocketGatewayData.MessageType.CONNECTING) {
            sendData(gatewayData, socketGatewayService);
            return;
        }

        if (gatewayData.getType() == SocketGatewayData.MessageType.EXIT) {
            sessions.remove(session);
            log.info("remove session={}", session);
            gatewayData.setMessage(gatewayData.getSocketName() + "님이 퇴장하였습니다.");
            sendData(gatewayData, socketGatewayService);
        }
    }

    public <T> void sendData(T message, SocketGatewayService socketGatewayService) {
        log.info("=====================SocketGatewayId sendMessage=====================");
        log.info("message={}", message);
        //모든 클라이언트들(session수 만큼)에게 메시지(데이터)를 전송
        for (WebSocketSession session : sessions) {
            log.info("session={}", session);
            socketGatewayService.sendData(session, message);
        }
    }
}
