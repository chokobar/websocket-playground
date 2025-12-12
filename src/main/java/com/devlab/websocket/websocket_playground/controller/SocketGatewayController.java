package com.devlab.websocket.websocket_playground.controller;

import com.devlab.websocket.websocket_playground.dto.SocketGatewayId;
import com.devlab.websocket.websocket_playground.service.SocketGatewayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/gateway")
public class SocketGatewayController {

    private final SocketGatewayService socketGatewayService;

    @PostMapping
    public SocketGatewayId creatGateway(@RequestParam String socketName) {
        log.info("==SocketGatewayController creatGateway==");
        return socketGatewayService.creatGateway(socketName);
    }

}
