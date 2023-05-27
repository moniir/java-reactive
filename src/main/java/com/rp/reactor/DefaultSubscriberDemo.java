/*
    custom way to emit data using fluxSink
 */
package com.rp.reactor;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class DefaultSubscriberDemo {
    public static void main(String[] args) {
        Flux.create(fluxSink -> {
            for (int i=0;i<10;i++){
                fluxSink.next(Util.faker().country().name());
            }
            fluxSink.complete();
        }).subscribe(Util.subscriber());
    }
}
