package com.rp.reactor.fluxCreate;

import com.rp.courseutil.Util;
import reactor.core.publisher.Flux;

public class FluxCreateRefactoring {
    public static void main(String[] args) {
        FluxCreateRefactoringHelper helper = new FluxCreateRefactoringHelper();
        Flux.create(helper).subscribe(Util.subscriber());

        Runnable runnable = helper::producer;
        for (int i=0;i<10;i++){
            new Thread(runnable).start();
        }
        Util.sleepSeconds(2);
    }
}
