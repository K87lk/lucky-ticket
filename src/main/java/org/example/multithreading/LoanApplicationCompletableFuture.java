package org.example.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LoanApplicationCompletableFuture {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        CompletableFuture<String> creditCheckFuture = CompletableFuture.supplyAsync(() ->
                "Credit check processed by " + Thread.currentThread().getName(), executorService);

        CompletableFuture<String> employmentCheckFuture = CompletableFuture.supplyAsync(() ->
                "Employment verification processed by " + Thread.currentThread().getName(), executorService);

        CompletableFuture<Void> allTasks = CompletableFuture.allOf(creditCheckFuture, employmentCheckFuture);

        allTasks.thenRun(() -> {
                    String creditCheck = creditCheckFuture.join();
                    String employmentCheck = employmentCheckFuture.join();
                    System.out.println(creditCheck);
                    System.out.println(employmentCheck);
                })
                .join();

        executorService.shutdown();
    }
}
