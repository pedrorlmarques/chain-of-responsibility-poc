package com.example.chainofresponsibility.domain;

public abstract class AbstractPostCallBack<T> {

    protected AbstractPostCallBack<T> nextCallBack;

    public void setNextCallBack(AbstractPostCallBack<T> nextCallBack) {
        this.nextCallBack = nextCallBack;
    }

    protected abstract void processRequest(T request);

    public void execute(T request) {

        this.processRequest(request);

        if (nextCallBack != null) {
            this.nextCallBack.execute(request);
        }
    }
}
