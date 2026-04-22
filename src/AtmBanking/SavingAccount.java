package AtmBanking;

public class SavingAccount extends Account {
    private double interestRate;

    public SavingAccount(String accNum, double balance, User owner, double interestRate) throws BankException {
        super(accNum, balance, owner);
        if (interestRate < 0) {
            throw new BankException("Interest rate cannot be negative.");
        }
        this.interestRate = interestRate;
    }

    @Override
    public boolean withdraw(double amount) throws BankException {
        if (amount <= 0) {
            throw new BankException("Withdrawal amount must be greater than zero.");
        }
        if (amount > getBalance()) {
            throw new BankException("Insufficient funds. Available: $" + getBalance());
        }
        setBalance(getBalance() - amount);
        addTransaction("Withdrew $" + amount + " | Balance: $" + getBalance());
        System.out.println("Withdrew $" + amount + ". Remaining balance: $" + getBalance());
        return true;
    }

    public void addInterest() throws BankException {
        double interest = getBalance() * interestRate;
        deposit(interest);
        System.out.println("Interest of $" + interest + " added at rate " + (interestRate * 100) + "%");
    }
}