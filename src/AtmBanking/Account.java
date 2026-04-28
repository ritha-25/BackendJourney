package AtmBanking;

import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Transactable {
    private String accountNumber;
    private double balance;
    private User owner;

    private List<String> transactionHistory = new ArrayList<>();

    public Account(String accountNumber, double balance, User owner) throws BankException {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new BankException("Account number cannot be empty.");
        }
        if (balance < 0) {
            throw new BankException("Opening balance cannot be negative.");
        }
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
        transactionHistory.add("Account opened with $" + balance);
    }

    @Override
    public void deposit(double amount) throws BankException {
        if (amount <= 0) {
            throw new BankException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        transactionHistory.add("Deposited $" + amount + " | Balance: $" + balance);
        System.out.println("Deposited $" + amount + ". New balance: $" + balance);
    }

    /**
     * Transfer money to another account
     */
    public boolean transfer(Account recipient, double amount) throws BankException {
        if (amount <= 0) {
            throw new BankException("Transfer amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new BankException("Insufficient funds for transfer. Available: $" + balance);
        }
        if (recipient == null) {
            throw new BankException("Recipient account not found.");
        }
        
        // Withdraw from this account
        this.balance -= amount;
        transactionHistory.add("Transferred $" + amount + " to " + recipient.getAccountNumber());
        
        // Deposit to recipient
        recipient.balance += amount;
        recipient.transactionHistory.add("Received $" + amount + " from " + this.accountNumber);
        
        System.out.println("Successfully transferred $" + amount + " to account " + recipient.getAccountNumber());
        return true;
    }

    public void printTransactionHistory() {
        System.out.println("\n--- Transaction History: " + accountNumber + " ---");
        for (String t : transactionHistory) {
            System.out.println(t);
        }
    }

    public void removeLastTransaction() {
        if (!transactionHistory.isEmpty()) {
            transactionHistory.remove(transactionHistory.size() - 1);
            System.out.println("Last transaction removed.");
        }
    }

    public double getBalance()          { return balance; }
    public String getAccountNumber()    { return accountNumber; }
    public User getOwner()              { return owner; }
    protected void setBalance(double b) { this.balance = b; }
    protected void addTransaction(String record) { transactionHistory.add(record); }
}