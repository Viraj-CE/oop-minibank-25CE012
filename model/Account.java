package model;

import java.util.Objects;

import model.annotation.Id;
import model.annotation.Positive;

public abstract class Account
        implements Transactable, InterestBearing {

    @Id
    private final String accountNumber;

    private String ownerName;

    @Positive
    private long balance;

    private boolean active;

    private static int accountCounter = 0;

    public abstract boolean canWithdraw();

    public Account(
            String ownerName,
            long openingBalance) {

        this.ownerName = ownerName;
        this.balance = openingBalance;
        this.accountNumber = generateAccountNumber();
        this.active = true;
    }

    public Account(String ownerName) {

        this(ownerName, 0);
    }

    private static String generateAccountNumber() {

        accountCounter++;

        return String.format(
                "AC%04d",
                accountCounter);
    }

    @Override
    public void deposit(long amount) {

        if (amount < 0) {
            return;
        }

        balance += amount;
    }

    @Override
    public boolean withdraw(long amount) {

        if (amount < 0) {
            return false;
        }

        if (amount <= balance) {

            balance -= amount;

            return true;
        }

        return false;
    }

    public static int getAccountCounter() {

        return accountCounter;
    }

    public String getAccountNumber() {

        return accountNumber;
    }

    public long getBalance() {

        return balance;
    }

    public String getOwnerName() {

        return ownerName;
    }

    public boolean isActive() {

        return active;
    }

    @Override
    public String toString() {

        return "Owner :" + ownerName
                + "'s account number is : "
                + accountNumber
                + " available balance is :"
                + balance;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Account)) {
            return false;
        }

        Account other = (Account) obj;

        return this.accountNumber.equals(
                other.accountNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(accountNumber);
    }
}