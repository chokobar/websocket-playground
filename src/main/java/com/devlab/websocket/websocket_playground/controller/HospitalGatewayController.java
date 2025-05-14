package com.devlab.websocket.websocket_playground.controller;

import com.devlab.websocket.websocket_playground.dto.HospitalGatewayId;
import com.devlab.websocket.websocket_playground.service.HospitalGatewayService;
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
public class HospitalGatewayController {

    private final HospitalGatewayService hospitalGatewayService;

    @PostMapping
    public HospitalGatewayId creatGateway(@RequestParam String hospitalName) {
        log.info("==HospitalGatewayController creatGateway==");
        return hospitalGatewayService.creatGateway(hospitalName);
    }

}
