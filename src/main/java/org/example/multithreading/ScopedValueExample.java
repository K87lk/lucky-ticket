package org.example.multithreading;

public class ScopedValueExample {
    private static final ScopedValue<String> REQUEST_ID = ScopedValue.newInstance();

    public static void main(String[] args) {
        ScopedValue.where(REQUEST_ID, "REQ-12345").run(() -> {
            handleRequest();
        });
    }
    private static void handleRequest() {
        log("Starting request");
        processService();
        log("Finished request");
    }

    private static void processService() {
        log("Processing service...");
        processRepository();
    }

    private static void processRepository() {
        log("Processing repository...");
    }

    private static void log(String message) {
        // Access the request ID within the current scope
        System.out.println("[" + REQUEST_ID.orElse("UNKNOWN") + "] " + message);
    }
}
