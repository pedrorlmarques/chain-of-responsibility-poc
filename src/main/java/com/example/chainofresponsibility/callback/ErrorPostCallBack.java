package com.example.chainofresponsibility.callback;

import com.example.chainofresponsibility.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class ErrorPostCallBack extends AbstractPostCallBack<Order> {

    @Override
    protected void processRequest(Order request) {
        throw new RuntimeException("Error Just Because Yes");
    }
}
