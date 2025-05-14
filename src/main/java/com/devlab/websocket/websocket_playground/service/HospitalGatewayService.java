package com.devlab.websocket.websocket_playground.service;

import com.devlab.websocket.websocket_playground.dto.HospitalGatewayId;
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
public class HospitalGatewayService {

    //JSON 데이터를 Java 객체로 변환하거나, Java 객체를 JSON 문자열 직렬화/역직렬화
    private final ObjectMapper objectMapper;

    private Map<String, HospitalGatewayId> hospitalGatewayIds;

    @PostConstruct
    private void init() {
        hospitalGatewayIds = new LinkedHashMap<>();
    }

    public HospitalGatewayId findGatewayId(String hospitalCd) {
        log.info("==HospitalGatewayService findGatewayId==");
        return hospitalGatewayIds.get(hospitalCd);
    }

    public HospitalGatewayId creatGateway(String hospitalName) {
        log.info("==HospitalGatewayService createGateway==");
        //중복되지 않는 값 생성
        String gatewayId = UUID.randomUUID().toString();
        //중복되지 않는 값을가지고 hospitalGatewayId 생성
        HospitalGatewayId hospitalGatewayId = HospitalGatewayId.builder()
                .hospitalCd(gatewayId)
                .hospitalName(hospitalName)
                .build();
        hospitalGatewayIds.put(gatewayId, hospitalGatewayId);
        return hospitalGatewayId;
    }

    public <T> void sendData(WebSocketSession session, T message) {
        log.info("==HospitalGatewayService sendData==");
        try {
            //message 객체를 JSON 형식으로 변환하여 클라이언트에게 전송
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error("데이터 전송 오류={}", e.getMessage());
        }
    }
}
