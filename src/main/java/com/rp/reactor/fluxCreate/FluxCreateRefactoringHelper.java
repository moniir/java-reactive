package com.rp.reactor.fluxCreate;

import com.github.javafaker.Faker;
import com.rp.courseutil.Util;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

public class FluxCreateRefactoringHelper implements Consumer<FluxSink<String>> {

    private FluxSink<String> fluxSink;
    @Override
    public void accept(FluxSink<String> stringFluxSink) {
        this.fluxSink = fluxSink;
    }
    public void producer(){
        System.out.println(Thread.currentThread().getName() +" : "+ Faker.instance().name().fullName());
    }

}
