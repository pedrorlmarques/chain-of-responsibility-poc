package com.example.chainofresponsibility.domain;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Log4j2
@Component
public class SendEmailPostCallBack extends AbstractPostCallBack<Order> {

    @Override
    protected void processRequest(Order request) {
        log.info("Send Email {}", request);

    }
}
