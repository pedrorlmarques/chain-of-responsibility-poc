package com.example.chainofresponsibility.callback;


import com.example.chainofresponsibility.domain.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SystemOutOrderPostCallBack extends AbstractPostCallBack<Order> {

    @Override
    protected void processRequest(Order request) {
        log.info("SystemOutOrder {}", request);
    }
}
