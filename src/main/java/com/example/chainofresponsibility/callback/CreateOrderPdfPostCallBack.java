package com.example.chainofresponsibility.callback;


import com.example.chainofresponsibility.domain.Order;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class CreateOrderPdfPostCallBack extends AbstractPostCallBack<Order> {

    @Override
    public void processRequest(Order request) {

        log.info("Create order {}", request);
    }
}
