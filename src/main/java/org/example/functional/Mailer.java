package org.example.functional;

import java.util.function.Consumer;

public class Mailer {
    private Mailer() {}
    public Mailer from(String addr) {
        System.out.println("from...");
        return this;
    }
    public Mailer to(String addr) {
        System.out.println("to...");
        return this;
    }
    public Mailer subject(String line) {
        System.out.println("subject...");
        return this;
    }
    public Mailer body(String body) {
        System.out.println("body...");
        return this;
    }
    public static void send(Consumer<Mailer> block) {
        Mailer mailer = new Mailer();

        block.accept(mailer);
        System.out.println("sending...");
    }

    public static void main(String[] args) {
        Mailer.send(mailer ->
                mailer.from("john@mayer.com")
                        .to("venkat@subramaniam.com")
                        .subject("Functional Programming in Java")
                        .body("Hi. Thanks for your explanation of functional programming in Java.")
        );
    }
}
