package model;

public class SavingsAccount extends Account {

    private long minBalance;

    public SavingsAccount(
            String name,
            long openingBalance,
            long minBalance) {

        super(name, openingBalance);

        this.minBalance = minBalance;
    }

    @Override
    public double interestRate() {

        return 4.0;
    }

    @Override
    public boolean canWithdraw() {

        return getBalance() >= minBalance;
    }
}