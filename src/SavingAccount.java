public class SavingAccount extends Account {
    private double interestRate;

    public SavingAccount(String accNum, double balance, User owner, double interestRate) {
        super(accNum, balance, owner);
        this.interestRate = interestRate;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > getBalance()) {
            System.out.println("Not enough money in account.");
            return false;
        }
        setBalance(getBalance() - amount);
        System.out.println("removed $" + amount + ". Remaining balance: $" + getBalance());
        return true;
    }

    public void addInterest() {
        double interest = getBalance() * interestRate;
        deposit(interest);
        System.out.println("Interest added!");
    }
}