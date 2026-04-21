package AtmBanking;

public class CurrentAccount extends Account {

    private double overdraftLimit;

    public CurrentAccount(String accNum, double balance, User owner, double overdraftLimit) throws BankException {
        super(accNum, balance, owner);
        if (overdraftLimit < 0) {
            throw new BankException("Overdraft limit cannot be negative.");
        }
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) throws BankException {
        if (amount <= 0) {
            throw new BankException("Withdrawal amount must be greater than zero.");
        }
        if (amount > getBalance() + overdraftLimit) {
            throw new BankException("Exceeds overdraft limit. Max you can withdraw: $" + (getBalance() + overdraftLimit));
        }
        setBalance(getBalance() - amount);
        System.out.println("Withdrew $" + amount + ". Remaining balance: $" + getBalance());
        return true;
    }

    public double getOverdraftLimit() { return overdraftLimit; }
}