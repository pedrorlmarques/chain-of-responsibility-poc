package com.example.chainofresponsibility.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;


@SpringBootTest
class AbstractPostCallBackTest {


    @Autowired
    private ApplicationContext applicationContext;


    // A->B ,, A X<-X B -> C
    @Test
    public void testChain() {

        var callbacks = List.of("createOrderPdfPostCallBack", "sendEmailPostCallBack", "systemOutOrder");

        AbstractPostCallBack initialBean = null;

        AbstractPostCallBack lastBean = null;

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
    }

}
