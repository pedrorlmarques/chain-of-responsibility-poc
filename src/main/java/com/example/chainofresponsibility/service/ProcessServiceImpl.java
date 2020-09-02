package com.example.chainofresponsibility.service;

import com.example.chainofresponsibility.domain.CreateOrderPostProcess;
import com.example.chainofresponsibility.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService {

    private final CreateOrderPostProcess createOrderPostProcess;

    @Override
    public void executeProcess(Order order) {
        this.createOrderPostProcess.getInitProcess().execute(order);
    }
}
