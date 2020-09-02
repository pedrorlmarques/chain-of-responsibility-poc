package com.example.chainofresponsibility.domain;

import com.example.chainofresponsibility.callback.AbstractPostCallBack;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AbstractPostCallBackTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testChain() {

        long before = System.currentTimeMillis();

        var callbacks = List.of("createOrderPdfPostCallBack", "sendEmailPostCallBack", "systemOutOrderPostCallBack");

        AbstractPostCallBack<Order> initialBean = null;

        AbstractPostCallBack<Order> lastBean = null;

        for (int i = 0; i < callbacks.size(); i++) {

            var currentBean = applicationContext.getBean(callbacks.get(i), AbstractPostCallBack.class);

            if (i == 0) {
                initialBean = currentBean;
            } else {
                lastBean.setNextCallBack(currentBean);
            }

            lastBean = currentBean;

        }

        initialBean.execute(new Order("123", "abc"));

        long now = System.currentTimeMillis();
        System.out.println("Seconds elapsed: " + (now - before) / 1000F + " seconds.");
    }

    @Test
    public void test2Chain() {

        long before = System.currentTimeMillis();

        var callBacks = List.of("createOrderPdfPostCallBack", "sendEmailPostCallBack", "systemOutOrderPostCallBack");

        var chainOfPostCallBacks = new ArrayList<AbstractPostCallBack>(callBacks.size());

        for (int i = 0; i < callBacks.size() - 1; i++) {

            var currentBean = applicationContext.getBean(callBacks.get(i), AbstractPostCallBack.class);
            currentBean.setNextCallBack(applicationContext.getBean(callBacks.get(i + 1), AbstractPostCallBack.class));
            chainOfPostCallBacks.add(currentBean);
        }

        chainOfPostCallBacks.get(0).execute(Order.builder().id("1").name("abc").build());

        long now = System.currentTimeMillis();
        System.out.println("Seconds elapsed: " + (now - before) / 1000F + " seconds.");

    }

}
