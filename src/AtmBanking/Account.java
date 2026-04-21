package AtmBanking;

public abstract class Account implements Transactable {
    private String accountNumber;
    private double balance;
    private User owner;

    public Account(String accountNumber, double balance, User owner) throws BankException {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new BankException("AtmBanking.Account number cannot be empty.");
        }
        if (balance < 0) {
            throw new BankException("Opening balance cannot be negative.");
        }
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }

    @Override
    public void deposit(double amount) throws BankException {
        if (amount <= 0) {
            throw new BankException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.println("Deposited $" + amount + ". New balance: $" + balance);
    }

    public double getBalance()          { return balance; }
    public String getAccountNumber()    { return accountNumber; }
    public User getOwner()              { return owner; }
    protected void setBalance(double b) { this.balance = b; }
}