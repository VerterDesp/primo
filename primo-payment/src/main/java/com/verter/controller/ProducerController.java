package com.verter.controller;

import com.verter.dto.PurchaseDTO;
import com.verter.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping("/notify")
    public void sendMessage(@RequestBody PurchaseDTO dto) {
        producerService.sendMessage(dto);
    }
}
