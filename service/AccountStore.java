package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import model.Account;

public class AccountStore {

    private HashMap<String, Account> map = new HashMap<String, Account>();
    private ArrayList<String> history = new ArrayList<String>();

    public void add(Account account) {
        map.put(account.getAccountNumber(), account);
    }

    public Account get(String accountNumber) {
        return map.get(accountNumber);
    }

    public ArrayList<Account> getAll() {
        return new ArrayList<Account>(map.values());
    }

    public ArrayList<Account> sortedByBalance() {
        ArrayList<Account> list = new ArrayList<Account>(map.values());
        Collections.sort(list, new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                if (o1.getBalance() > o2.getBalance()) return -1;
                if (o1.getBalance() < o2.getBalance()) return 1;
                return 0;
            }
        });
        return list;
    }

    public void addHistory(String s) {
        history.add(s);
    }

    public ArrayList<String> getHistory() {
        return history;
    }
}
