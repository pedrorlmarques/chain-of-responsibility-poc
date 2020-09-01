package com.example.chainofresponsibility.domain;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SystemOutOrder extends AbstractPostCallBack<Order> {

    @Override
    protected void processRequest(Order request) {
        log.info("SystemOutOrder {}", request);
    }
}
