package org.example.multithreading;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LoanApplicationExecutorService {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Future<String> creditScoreCheck = executor.submit(() -> {
            System.out.println("Credit score check...");
            Thread.sleep(200);
            return "Credit score is good...";
        });

        Future<String> employmentVerificationCheck = executor.submit(() -> {
            System.out.println("Employment verification check...");
            Thread.sleep(200);
            return "Employment verification is successful...";
        });


        String creditCheck = creditScoreCheck.get();
        String employmentCheck = employmentVerificationCheck.get();

        System.out.println(creditCheck);
        System.out.println(employmentCheck);

        executor.shutdown();
    }
}
