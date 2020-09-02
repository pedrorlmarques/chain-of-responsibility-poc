package com.example.chainofresponsibility.service;

import com.example.chainofresponsibility.domain.Order;

public interface ProcessService {

    public void executeProcess(Order order);
}
