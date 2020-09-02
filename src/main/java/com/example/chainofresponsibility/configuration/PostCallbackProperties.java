package com.example.chainofresponsibility.configuration;

import com.example.chainofresponsibility.callback.AbstractPostCallBack;
import com.example.chainofresponsibility.domain.CreateOrderPostProcess;
import com.example.chainofresponsibility.domain.Order;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;

@Log4j2
@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "post-callback")
@RequiredArgsConstructor
public class PostCallbackProperties implements Validator {

    private final GenericApplicationContext genericApplicationContext;

    @Valid
    private List<String> processes;

    @Override
    public boolean supports(Class<?> aClass) {
        return PostCallbackProperties.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "processes", "field.required");

        var postCallbackProperties = (PostCallbackProperties) o;

        var callBacks = postCallbackProperties.getProcesses();

        AbstractPostCallBack<Order> initialBean = null;

        AbstractPostCallBack<Order> lastBean = null;

        for (int i = 0; i < callBacks.size(); i++) {

            AbstractPostCallBack<Order> currentBean;

            try {
                currentBean = genericApplicationContext.getBean(callBacks.get(i), AbstractPostCallBack.class);
            } catch (BeansException exception) {
                log.error(exception);
                errors.rejectValue("processes", "field.required",
                        new Object[]{AbstractPostCallBack.class.getSimpleName()}, exception.getLocalizedMessage());
                return;
            }

            if (i == 0) {
                initialBean = currentBean;
            } else {
                lastBean.setNextCallBack(currentBean);
            }

            lastBean = currentBean;
        }

        genericApplicationContext.registerBean(CreateOrderPostProcess.class, initialBean);
    }
}
