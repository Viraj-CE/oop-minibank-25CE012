package test;

import model.SavingsAccount;
import service.BankService;

public class BankServiceTest {

    public static void main(String[] args) {

        BankService service = new BankService();
        SavingsAccount acc = new SavingsAccount("Test", 1000, 500);

        try {
            service.openAccount(acc);
            service.deposit(acc.getAccountNumber(), 500);
            System.out.println(acc.getBalance());
        } catch (Exception e) {

        }

        try {
            service.withdraw(acc.getAccountNumber(), 2000);
        } catch (Exception e) {
            System.out.println("Exception");
        }
    }
}
