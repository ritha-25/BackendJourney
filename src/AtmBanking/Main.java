package AtmBanking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {

            User prince   = new User("p09", "Prince", "098");
            User nzaramba = new User("p10", "Nzaramba", "456");

            Account savings = new SavingAccount("SA001", 1000.0, prince, 0.05);
            Account current = new CurrentAccount("CA001", 500.0, nzaramba, 200.0);

            Bank bank = new Bank();
            bank.addAccount(savings);
            bank.addAccount(current);

            Atm atm = new Atm();

            System.out.print("Enter account number: ");
            String accNumber = scanner.nextLine();

            Account found = bank.getAccount(accNumber);
            if (found == null) {
                System.out.println("Account not found.");
                return;
            }

            System.out.print("Enter PIN: ");
            String pin = scanner.nextLine();

            boolean loggedIn = atm.login(found, pin);
            if (!loggedIn) return;

            boolean running = true;
            while (running) {
                System.out.println("\n ATM Menu ");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transaction History");
                System.out.println("5. Exit");
                System.out.print("Choose option: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        atm.checkBalance();
                        break;
                    case "2":
                        System.out.print("Enter deposit amount: ");
                        double depositAmt = Double.parseDouble(scanner.nextLine());
                        atm.deposit(depositAmt);
                        break;
                    case "3":
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmt = Double.parseDouble(scanner.nextLine());
                        atm.withdrawal(withdrawAmt);
                        break;
                    case "4":
                        found.printTransactionHistory();
                        break;
                    case "5":
                        System.out.println("Thank you for using our ATM. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 1-5.");
                }
            }

        } catch (BankException e) {
            System.out.println("System error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered. Please enter a number.");
        } finally {
            scanner.close();
        }
    }
}