package service;

import model.Account;
import repository.Repository;
import exception.BankException;
import exception.InvalidAmountException;
import util.Validator;

public class BankService {

    private Repository<Account> repo = new Repository<Account>();

    public void openAccount(Account account) {
        repo.save(account);
    }

    public Account getAccount(String accountNumber) {
        return repo.findById(accountNumber);
    }

    public java.util.ArrayList<Account> listAccounts() {
        return repo.findAll();
    }

    public void deposit(String accountNumber, long amount) throws BankException {
        Account acc = repo.findById(accountNumber);
        if (acc == null) {
            throw new exception.AccountNotFoundException("Account not found");
        }
        acc.deposit(amount);
    }

    public void withdraw(String accountNumber, long amount) throws BankException {
        Account acc = repo.findById(accountNumber);
        if (acc == null) {
            throw new exception.AccountNotFoundException("Account not found");
        }
        acc.withdraw(amount);
    }

    public void transfer(String fromAcc, String toAcc, long amount) throws BankException {
        Account from = repo.findById(fromAcc);
        Account to = repo.findById(toAcc);
        if (from == null || to == null) {
            throw new exception.AccountNotFoundException("Account not found");
        }
        if (amount <= 0) {
            throw new InvalidAmountException("Invalid amount");
        }
        from.withdraw(amount);
        to.deposit(amount);
    }

    public String statement(String accountNumber) {
        Account acc = repo.findById(accountNumber);
        if (acc == null) {
            return "Account not found";
        }
        return util.StatementFormatter.buildStatement(acc);
    }
}
