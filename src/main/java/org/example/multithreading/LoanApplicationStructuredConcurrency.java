package org.example.multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

public class LoanApplicationStructuredConcurrency {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        try(var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            var creditScoreFuture = scope.fork(() -> {
                System.out.println("Credit check running..." + Thread.currentThread());
                return "Credit check running...";
            });
            var employmentScopeFuture = scope.fork(() -> {
                System.out.println("Employment check running..." + Thread.currentThread());
                return "Employment check running...";
            });
            scope.join();
            scope.throwIfFailed();
            }
        }
    }

