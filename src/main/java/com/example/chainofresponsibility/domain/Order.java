package com.example.chainofresponsibility.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Order {

    private String id;
    private String name;
}
