package org.example.multithreading;

public class Multithreading {
    public static void main(String[] args) throws InterruptedException {
        Thread creditScoreThread = new Thread(() -> {
            System.out.println(10 / 0);
            System.out.println("Credit check running..." + Thread.currentThread().getName());
        });
        Thread employmentVerificationThread = new Thread(() -> {
            System.out.println("Employment verification running..." + Thread.currentThread().getName());
        });

        creditScoreThread.start();
        employmentVerificationThread.start();

        creditScoreThread.join();
        employmentVerificationThread.join();

        if (!creditScoreThread.isAlive() || !employmentVerificationThread.isAlive()) {
            System.out.println("Verification failed...");
        } else {
            System.out.println("Verification passed...");
        }
    }
}
