import java.util.Scanner;

record BankInfo(String name, String branch) {}

enum MenuOption {

    OPEN_ACCOUNT,
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    EXIT

}

class MiniBank {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        BankInfo bank = new BankInfo("HDFC", "Surat");
        
        System.out.println(bank);

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

            menu = switch(choice) {
                case 1 -> MenuOption.OPEN_ACCOUNT;
                case 2 -> MenuOption.DEPOSIT;
                case 3 -> MenuOption.WITHDRAW;
                case 4 -> MenuOption.TRANSFER;
                case 5 -> MenuOption.EXIT;              
                default -> null;
            };
        }while(choice != 5);

    }
}