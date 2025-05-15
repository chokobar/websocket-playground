package com.devlab.websocket.websocket_playground;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket    //Web Socket 활성화 어노테이션
public class WebSocketConfig implements WebSocketConfigurer {

    private final WebSocketHandler webSocketHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        //Version 1.0
        //Talend API 확장프로그램 실행 -> Web Socket Testing  ws://localhost:8080/ws/gateway
        //registry.addHandler(webSocketHandler, "ws/gateway").setAllowedOrigins("*");

        //Version 2.0
        //http://localhost:8080/index.html
        //.setAllowedOrigins("*") 모든 도메인에서 WebSocket 요청을 허용 (CORS 설정)
        registry.addHandler(webSocketHandler, "/ws-gateway").setAllowedOrigins("*");
    }
}
