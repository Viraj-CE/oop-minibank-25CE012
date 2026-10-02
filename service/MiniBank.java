package service;

import java.util.Scanner;

import model.Account;
import model.BankInfo;
import model.SavingsAccount;
import util.ReportGenerator;
import util.StatePersister;
import util.TransactionLog;

import static util.Validator.isValidMobile;

public class MiniBank {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo("HDFC", "Surat");

        System.out.println(bank);

        BankService service = new BankService();

        java.nio.file.Path data = java.nio.file.Paths.get("data", "accounts.dat");
        try {
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get("data"));
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get("logs"));
        } catch (Exception e) {

        }

        Account[] loaded = StatePersister.load(data);
        if (loaded != null) {
            for (Account a : loaded) {
                service.openAccount(a);
            }
        }

        System.out.println("\nMobile Validation:");
        System.out.println(isValidMobile("9876543210"));

        int choice;

        do {

            System.out.println("\n=============== Menu ===============");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Report");
            System.out.println("6. Exit");
            System.out.println("====================================");

            System.out.print("Enter your choice : ");

            choice = sc.nextInt();

            if (choice == 1) {
                Account acc = new SavingsAccount("Viraj", 5000, 1000);
                service.openAccount(acc);
                System.out.println(acc);
            } else if (choice == 2) {
                System.out.print("Enter account number : ");
                String accNo = sc.next();
                System.out.print("Enter amount : ");
                long amt = sc.nextLong();
                try {
                    service.deposit(accNo, amt);
                    TransactionLog.append(java.nio.file.Paths.get("logs", "log.txt"), "DEPOSIT " + accNo + " " + amt);
                    System.out.println("Deposited");
                } catch (Exception e) {
                    System.out.println("Failed");
                }
            } else if (choice == 3) {
                System.out.print("Enter account number : ");
                String accNo = sc.next();
                System.out.print("Enter amount : ");
                long amt = sc.nextLong();
                try {
                    service.withdraw(accNo, amt);
                    TransactionLog.append(java.nio.file.Paths.get("logs", "log.txt"), "WITHDRAW " + accNo + " " + amt);
                    System.out.println("Withdrawn");
                } catch (Exception e) {
                    System.out.println("Failed");
                }
            } else if (choice == 4) {
                System.out.print("From : ");
                String from = sc.next();
                System.out.print("To : ");
                String to = sc.next();
                System.out.print("Amount : ");
                long amt = sc.nextLong();
                try {
                    service.transfer(from, to, amt);
                    TransactionLog.append(java.nio.file.Paths.get("logs", "log.txt"), "WITHDRAW " + from + " " + amt);
                    TransactionLog.append(java.nio.file.Paths.get("logs", "log.txt"), "DEPOSIT " + to + " " + amt);
                    System.out.println("Transferred");
                } catch (Exception e) {
                    System.out.println("Failed");
                }
            } else if (choice == 5) {
                ReportGenerator.generate();
                System.out.println("Report generated");
            }

        } while (choice != 6);

        java.util.ArrayList<Account> list = service.listAccounts();
        Account[] arr = list.toArray(new Account[0]);
        StatePersister.save(arr, data);

        sc.close();

        System.out.println("Thank you for using MiniBank.");
    }
}