package com.devlab.websocket.websocket_playground.handler;

import com.devlab.websocket.websocket_playground.dto.SocketGatewayData;
import com.devlab.websocket.websocket_playground.dto.SocketGatewayId;
import com.devlab.websocket.websocket_playground.service.SocketGatewayService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketGatewayHandler extends TextWebSocketHandler {
    //TextWebSocketHandler JSON 문자열 전송 가능
    private final SocketGatewayService socketGatewayService;

    //JSON 데이터를 Java 객체로 변환하거나, Java 객체를 JSON 문자열 직렬화/역직렬화
    private final ObjectMapper objectMapper;

    @Override
    protected void handleTextMessage(WebSocketSession session , TextMessage message) throws Exception {
        //WebSocketSession은 WebSocket 연결을 관리하는 객체
        //TextMessage는 WebSocket을 통해 텍스트 메시지를 주고받을 때 사용되는 객체
        //getPayload 메서드 Spring WebSocket의 TextMessage 클래스에서 제공하는 메서드로,
        //웹소켓을 통해 받은 메시지의 내용을 문자열(String)로 반환하는 역할
        //클라이언트가 보낸 JSON 문자열 등을 이 메서드로 꺼ㅏ
        String payload = message.getPayload();
        log.info("payload={}", payload);

        //objectMapper.readValue는 payload에서 받은 문자열을 java객체로 변환
        SocketGatewayData socketData = objectMapper.readValue(payload, SocketGatewayData.class);
        SocketGatewayId gatewayId = socketGatewayService.findGatewayId(socketData.getSocketCd());

        //gatewayId가 일치하지 않으면 해당 로직
        if (gatewayId == null) {
            log.error("Gateway ID를 찾을 수 없습니다. 소켓코드: {}", socketData.getSocketCd());
            return;
        }

        gatewayId.handleActions(session, socketData, socketGatewayService);
    }
}
