import java.util.Scanner;
import java.util.Objects;
record BankInfo(String name, String branch) {
}

enum MenuOption {

    OPEN_ACCOUNT,
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    EXIT

}

class Customer implements Cloneable {
    private String name;
    private String email;
    private String mobile;
    private final String customerId;
    private Address address;
    private static long customerCounter = 0;

    Customer(String name, String email, String mobile, Address address) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.customerId = generateCustomerId();
        this.address = address;
    }
    public static class Address {
        private String line;
        private String city;
        private String pincode;

        Address(String line, String city, String pincode) {
            this.line = line;
            this.city = city;
            this.pincode = pincode;
        }
        public String getCity() {
            return city;
        }
        public String getLine() {
            return line;
        }
        public String getPincode() {
            return pincode;
        }
    }
    public Customer clone() {
        try {
            return (Customer) super.clone();
        }
        catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
    private static String generateCustomerId() {
        customerCounter++;
        return "CUST" + (customerCounter + 100);
    }

    public static long getCustomerCounter() {
        return customerCounter;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }
}

class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;
    private static int accountCounter = 0;

    Account(String ownerName, long openingBalance) {
        this.ownerName = ownerName;
        this.balance = openingBalance;
        this.accountNumber = GenerateAccountNumber();
        this.active = true;
    }

    Account(String ownerName) {
        this(ownerName, 0);
    }

    private static String GenerateAccountNumber() {
        accountCounter++;
        return String.format("AC%04d", accountCounter);
    }

    public void deposit(long amount) {
        if (amount < 0) {
            return;
        }
        balance += amount;
    }

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

    public String toString() {
        return "Owner :" + ownerName + "'s account number is : " + accountNumber + " available balance is :" + balance;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(!(obj instanceof Account)) {
            return false;
        }

        Account other = (Account) obj;
        return this.accountNumber.equals(other.accountNumber);
    }
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}

class MiniBank {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo("HDFC", "Surat");

        System.out.println(bank);
        Account[] accounts = new Account[3];
        accounts[0] = new Account("Smit", 1500);
        accounts[1] = new Account("Dhruv", 2000);
        accounts[2] = new Account("Harsh");

        accounts[0].deposit(500);
        accounts[1].withdraw(500);
        accounts[2].deposit(1500);
        for(int i=0; i<3; i++) {
            System.out.println(accounts[i].getBalance());
        }


        MenuOption menu = null;
        int choice;
        do {

            System.out.println("=============== Menu ===============");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.println("====================================");
            System.out.print("Enter your choice :");
            choice = sc.nextInt();

            menu = switch (choice) {
                case 1 -> MenuOption.OPEN_ACCOUNT;
                case 2 -> MenuOption.DEPOSIT;
                case 3 -> MenuOption.WITHDRAW;
                case 4 -> MenuOption.TRANSFER;
                case 5 -> MenuOption.EXIT;
                default -> null;
            };
        } while (choice != 5);

    }
}