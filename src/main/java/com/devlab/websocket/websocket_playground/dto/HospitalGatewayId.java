package com.devlab.websocket.websocket_playground.dto;

import com.devlab.websocket.websocket_playground.service.HospitalGatewayService;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Data
public class HospitalGatewayId  {

    private String hospitalCd;

    private String hospitalName;

    private Set<WebSocketSession> sessions = new HashSet<>();

    @Builder
    public HospitalGatewayId(String hospitalCd, String hospitalName) {
        this.hospitalCd = hospitalCd;
        this.hospitalName = hospitalName;
    }

    public void handleActions(
            WebSocketSession session, HospitalGatewayData gatewayData, HospitalGatewayService hospitalGatewayService ) {
        //WebSocketSession은 WebSocket 연결을 관리하는 객체
        log.info("=====================HospitalGatewayId handleActions=====================");
        if (gatewayData.getType().equals(HospitalGatewayData.MessageType.JOIN)) {
            sessions.add(session);
            log.info("add session={}", session);
            gatewayData.setMessage((gatewayData.getHospitalName() + "님이 입장하였습니다."));
        } else if (gatewayData.getType().equals(HospitalGatewayData.MessageType.EXIT)) {
            sessions.remove(session);
            log.info("remove session={}", session);
            gatewayData.setMessage((gatewayData.getHospitalName() + "님이 퇴장하였습니다."));
        }
        log.info("gatewayData={}", gatewayData);
        sendData(gatewayData , hospitalGatewayService);
    }

    public <T> void sendData(T message, HospitalGatewayService hospitalGatewayService) {
        log.info("=====================HospitalGatewayId sendMessage=====================");
        log.info("message={}", message);
        //모든 클라이언트들(session수 만큼)에게 메시지(데이터)를 전송
        for (WebSocketSession session : sessions) {
            log.info("session={}", session);
            hospitalGatewayService.sendData(session, message);
        }
    }
}
