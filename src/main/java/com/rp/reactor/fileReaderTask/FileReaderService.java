package com.rp.reactor.fileReaderTask;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SynchronousSink;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class FileReaderService {

    private Callable<BufferedReader> openReader(Path path){
        return ()-> Files.newBufferedReader(path);
    }

    private BiFunction<BufferedReader, SynchronousSink<String>,BufferedReader> read(){
        return (br, sink)->{
            String line = null;
            try {
                line = br.readLine();
                if(Objects.isNull(line)){
                    sink.complete();
                }else {
                    sink.next(line);
                }
            } catch (IOException e) {
                sink.error(e);
            }
            return br;
        };
    }

    private Consumer<BufferedReader> closeReader(){
        return br->{
            try {
                br.close();
                System.out.println("--closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }


    public Flux<String> read(Path path){
        return Flux.generate(openReader(path),read(),closeReader());
    }
}
