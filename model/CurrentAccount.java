package model;

public class CurrentAccount extends Account {

    private long overdraftLimit;

    public CurrentAccount(
            String name,
            long openingBalance,
            long overdraftLimit) {

        super(name, openingBalance);

        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public double interestRate() {

        return 0;
    }

    @Override
    public boolean canWithdraw() {

        return getBalance() >= -overdraftLimit;
    }
}