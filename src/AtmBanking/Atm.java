package AtmBanking;

import java.util.HashSet;
import java.util.Set;

public class Atm {
    private Account currentAccount;

    private Set<String> loggedInUsers = new HashSet<>();

    public boolean login(Account account, String pin) {
        if (account.getOwner().checkPin(pin)) {
            this.currentAccount = account;
            loggedInUsers.add(account.getOwner().getName());
            System.out.println("Welcome, " + account.getOwner().getName() + "!");
            return true;
        }
        System.out.println("Incorrect PIN. Access denied.");
        return false;
    }

    public void deposit(double amount) {
        try {
            currentAccount.deposit(amount);
        } catch (BankException e) {
            System.out.println("Deposit failed: " + e.getMessage());
        }
    }

    public void withdrawal(double amount) {
        try {
            currentAccount.withdraw(amount);
        } catch (BankException e) {
            System.out.println("Withdrawal failed: " + e.getMessage());
        }
    }

    public void transfer(Account recipient, double amount) {
        try {
            currentAccount.transfer(recipient, amount);
        } catch (BankException e) {
            System.out.println("Transfer failed: " + e.getMessage());
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + currentAccount.getBalance());
    }

    public void showLoggedInUsers() {
        System.out.println("\n Users who used this ATM ");
        for (String user : loggedInUsers) {
            System.out.println(user);
        }
    }

    public void removeUser(String name) {
        loggedInUsers.remove(name);
        System.out.println(name + " removed from session log.");
    }
}