package com.example.chainofresponsibility.domain;

import com.example.chainofresponsibility.callback.AbstractPostCallBack;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class CreateOrderPostProcess {

    private final AbstractPostCallBack<Order> initProcess;
}
