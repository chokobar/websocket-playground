package com.devlab.websocket.websocket_playground.dto;

import lombok.Data;

@Data
public class SocketGatewayData {

    // CONNECT : socketCd로 입장, TALK : 대화 시작, EXIT : 퇴장
    public enum MessageType {
        CONNECT , TALK , EXIT
    }

    private MessageType type;

    private String socketCd;

    private String socketName;

    private String message;
}
