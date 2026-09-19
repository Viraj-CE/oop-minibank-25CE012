package model;

public class FixedDeposit extends Account {

    public FixedDeposit(
            String name,
            long openingBalance) {

        super(name, openingBalance);
    }

    @Override
    public double interestRate() {

        return 7.0;
    }

    @Override
    public boolean canWithdraw() {

        return false;
    }
}