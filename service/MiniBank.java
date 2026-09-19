package service;

import java.util.Scanner;

import model.Account;
import model.BankInfo;
import model.SavingsAccount;
import model.WithdrawRule;

import static util.Validator.isValidMobile;

public class MiniBank {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo("HDFC", "Surat");

        System.out.println(bank);

        // =========================================
        // WithdrawRule using Anonymous Class
        // =========================================

        WithdrawRule rule1 = new WithdrawRule() {

            @Override
            public boolean allow(Account account, long amount) {

                return amount > 0
                        && amount <= account.getBalance()
                        && account.canWithdraw();
            }
        };

        // =========================================
        // WithdrawRule using Lambda
        // =========================================

        WithdrawRule rule2 = (account, amount) ->
                amount > 0
                && amount <= account.getBalance()
                && account.canWithdraw();

        // =========================================
        // Create Account
        // =========================================

        Account account =
                new SavingsAccount(
                        "Viraj",
                        5000,
                        1000
                );

        System.out.println("\nAccount:");
        System.out.println(account);

        // =========================================
        // Test Anonymous Class
        // =========================================

        System.out.println("\nAnonymous Class Rule:");

        if (rule1.allow(account, 2000)) {

            System.out.println("Withdrawal allowed");

        } else {

            System.out.println("Withdrawal denied");
        }

        // =========================================
        // Test Lambda
        // =========================================

        System.out.println("\nLambda Rule:");

        if (rule2.allow(account, 2000)) {

            System.out.println("Withdrawal allowed");

        } else {

            System.out.println("Withdrawal denied");
        }

        // =========================================
        // Static Import Example
        // =========================================

        System.out.println("\nMobile Validation:");

        System.out.println(
                isValidMobile("9876543210")
        );

        // =========================================
        // Menu
        // =========================================

        int choice;

        do {

            System.out.println("\n=============== Menu ===============");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.println("====================================");

            System.out.print("Enter your choice : ");

            choice = sc.nextInt();

        } while (choice != 5);

        sc.close();

        System.out.println("Thank you for using MiniBank.");
    }
}