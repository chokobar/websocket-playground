package com.devlab.websocket.websocket_playground.dto;

import lombok.Data;

@Data
public class SocketGatewayData {

    // FIRST_CONNECT : socketCd로 입장, CONNECTING : 대화 시작, EXIT : 퇴장
    public enum MessageType {
        FIRST_CONNECT , CONNECTING , EXIT
    }

    private MessageType type;

    private String socketCd;

    private String socketName;

    private String message;
}
