package com.rp.reactor;

import com.github.javafaker.Faker;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class StockPriceObserver {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        CountDownLatch latch = new CountDownLatch(1);
        Flux.interval(Duration.ofSeconds(1))
                .map(i-> atomicInteger.getAndAccumulate(Faker
                        .instance()
                        .random()
                        .nextInt(-5,5),
                        (a,b)->a+b)).subscribeWith(new Subscriber<Integer>() {

                    private Subscription subscription;
                    @Override
                    public void onSubscribe(Subscription subscription) {
                        this.subscription = subscription;
                        subscription.request(Long.MAX_VALUE);

                    }

                    @Override
                    public void onNext(Integer price) {
                        System.out.println(LocalDateTime.now() +" :price :"+price);
                        if(price>115 || price<90){
                            subscription.cancel();
                            latch.countDown();
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        latch.countDown();
                    }

                    @Override
                    public void onComplete() {
                        latch.countDown();
                    }
                });
        latch.await();
    }
}
