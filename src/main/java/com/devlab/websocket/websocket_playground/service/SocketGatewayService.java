package com.devlab.websocket.websocket_playground.service;

import com.devlab.websocket.websocket_playground.dto.SocketGatewayId;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocketGatewayService {

    //JSON 데이터를 Java 객체로 변환하거나, Java 객체를 JSON 문자열 직렬화/역직렬화
    private final ObjectMapper objectMapper;

    private Map<String, SocketGatewayId> socketGatewayIds;

    @PostConstruct
    private void init() {
        socketGatewayIds = new LinkedHashMap<>();
    }

    public SocketGatewayId findGatewayId(String socketCd) {
        log.info("==SocketGatewayService findGatewayId==");
        return socketGatewayIds.get(socketCd);
    }

    public SocketGatewayId creatGateway(String socketName) {
        log.info("==SocketGatewayService createGateway==");
        //중복되지 않는 값 생성
        String gatewayId = UUID.randomUUID().toString();
        //중복되지 않는 값을가지고 socketGatewayId 생성
        SocketGatewayId socketGatewayId = SocketGatewayId.builder()
                .socketCd(gatewayId)
                .socketName(socketName)
                .build();
        socketGatewayIds.put(gatewayId, socketGatewayId);
        return socketGatewayId;
    }

    public <T> void sendData(WebSocketSession session, T message) {
        log.info("==SocketGatewayService sendData==");
        try {
            //message 객체를 JSON 형식으로 변환하여 클라이언트에게 전송
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error("데이터 전송 오류={}", e.getMessage());
        }
    }
}
