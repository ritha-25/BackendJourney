package AtmBanking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {

            User prince   = new User("p09", "Prince", "098");
            User nzaramba = new User("N10", "Nzaramba", "456");
            User neza = new User("N11", "Neza", "46");

            Bank bank = new Bank();
            bank.addUser(prince);
            bank.addUser(nzaramba);
            bank.addUser(neza);


            Account savings = new SavingAccount("SA001", 1000.0, prince, 10);
            Account current = new CurrentAccount("CA001", 500.0, nzaramba, 200.0);

            bank.addAccount(savings);
            bank.addAccount(current);

            Atm atm = new Atm();

            System.out.print("\nEnter your account number: ");
            String accNumber = scanner.nextLine();

            Account found = bank.getAccount(accNumber);
            if (found == null) {
                System.out.println("Sorry, we couldn't find that account in our system.");
                return;
            }

            System.out.print("Enter your PIN: ");
            String pin = scanner.nextLine();

            boolean loggedIn = atm.login(found, pin);
            if (!loggedIn) return;

            boolean running = true;
            while (running) {
                System.out.println("\n======= ATM Menu =======");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Transfer Money");
                System.out.println("5. View Transaction History");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        atm.checkBalance();
                        break;
                    case "2":
                        System.out.print("How much would you like to deposit? ");
                        double depositAmt = Double.parseDouble(scanner.nextLine());
                        atm.deposit(depositAmt);
                        bank.saveData();
                        break;
                    case "3":
                        System.out.print("How much would you like to withdraw? ");
                        double withdrawAmt = Double.parseDouble(scanner.nextLine());
                        atm.withdrawal(withdrawAmt);
                        bank.saveData();
                        break;
                    case "4":
                        System.out.println("\n--- Transfer Money ---");
                        bank.listAllAccounts();
                        System.out.print("Enter the account number you want to send money to: ");
                        String recipientAcc = scanner.nextLine();
                        Account recipient = bank.getAccount(recipientAcc);
                        if (recipient == null) {
                            System.out.println("Account not found. Please check the account number.");
                        } else if (recipient.getAccountNumber().equals(found.getAccountNumber())) {
                            System.out.println("You cannot transfer money to your own account.");
                        } else {
                            System.out.print("How much would you like to transfer? ");
                            double transferAmt = Double.parseDouble(scanner.nextLine());
                            atm.transfer(recipient, transferAmt);
                            bank.saveData();
                        }
                        break;
                    case "5":
                        found.printTransactionHistory();
                        break;
                    case "6":
                        System.out.println("\nThank you for banking with us! Have a great day!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose 1-6.");
                }
            }

        } catch (BankException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered. Please enter a valid number.");
        } finally {
            scanner.close();
        }
    }
}