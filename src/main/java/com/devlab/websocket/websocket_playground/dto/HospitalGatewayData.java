package com.devlab.websocket.websocket_playground.dto;

import lombok.Data;

@Data
public class HospitalGatewayData  {

    // JOIN : hospitalCd로 입장, TALK : 대화 시작, EXIT : 퇴장
    public enum MessageType {
        JOIN , TALK , EXIT
    }

    private MessageType type;

    private String hospitalCd;

    private String hospitalName;

    private String message;
}
