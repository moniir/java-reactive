package com.rp.reactor;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class DefaultSubscriber implements Subscriber<Object> {

    private String name = "";

    public DefaultSubscriber(String name) {
        this.name = name+" - ";
    }
    public DefaultSubscriber(){}

    private Subscription subscription;
    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(Long.MAX_VALUE);

    }

    @Override
    public void onNext(Object o) {
        System.out.println(name +"Received: "+o);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("Completed");
    }
}
