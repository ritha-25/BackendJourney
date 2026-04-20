public abstract class Account {
    private String accountNumber;
    private double balance;
    private User owner;

    public Account(String accountNumber, double balance, User owner) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("The amount is incorrect.");
            return;
        }
        balance += amount;
        System.out.println("You deposited $" + amount + " and your new balance is $" + balance);
    }

    public abstract boolean withdraw(double amount);

    public double getBalance()          { return balance; }
    public String getAccountNumber()    { return accountNumber; }
    public User getOwner()              { return owner; }
    protected void setBalance(double b) { this.balance = b; }
}