package service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.Account;

public class TransactionProcessor {

    private ExecutorService executor;
    private ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<String, Account>();

    public TransactionProcessor(int poolSize) {
        executor = Executors.newFixedThreadPool(poolSize);
    }

    public void submit(Runnable task) {
        executor.execute(task);
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public ConcurrentHashMap<String, Account> getAccounts() {
        return accounts;
    }

    public void stop() {
        executor.shutdown();
        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (Exception e) {

        }
    }
}
