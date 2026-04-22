package AtmBanking;

import java.util.HashMap;
import java.util.Map;

public class Bank {

    private Map<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
        System.out.println("Account " + account.getAccountNumber() + " registered.");
    }

    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void removeAccount(String accountNumber) {
        accounts.remove(accountNumber);
        System.out.println("Account " + accountNumber + " removed.");
    }

    public void listAllAccounts() {
        System.out.println(" ll Accounts ");
        for (Account account : accounts.values()) {
            System.out.println("Number: " + account.getAccountNumber()
                    + " | Owner: " + account.getOwner().getName()
                    + " | Balance: $" + account.getBalance());
        }
    }
}