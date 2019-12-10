package verticals;

import io.vertx.core.AbstractVerticle;

public class HelloWorldVertical extends AbstractVerticle {

    public void start() {
        System.out.println(Thread.currentThread() + ": My first vertical started!");
    }

    public void stop() {
        System.out.println("My first vertical stopped!");
    }

}
