package service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TransactionProcessor {

    private ExecutorService executor;

    public TransactionProcessor(int poolSize) {
        executor = Executors.newFixedThreadPool(poolSize);
    }

    public void submit(Runnable task) {
        executor.execute(task);
    }

    public void stop() {
        executor.shutdown();
        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (Exception e) {

        }
    }
}
