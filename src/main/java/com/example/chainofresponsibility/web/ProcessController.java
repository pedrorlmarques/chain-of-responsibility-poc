package com.example.chainofresponsibility.web;

import com.example.chainofresponsibility.domain.Order;
import com.example.chainofresponsibility.service.ProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
public class ProcessController {

    private final ProcessService processService;

    @GetMapping("/")
    public void start() {
        log.info("Yellow....");
        this.processService.executeProcess(Order.builder().id("123").name("Joy").build());
    }
}
