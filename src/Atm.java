public class Atm {
    public Account currentAccount;

    public boolean login(Account account, String pin) {
        if (account.getOwner().checkPin(pin)) {
            this.currentAccount = account;
            System.out.println("Welcome " + account.getOwner().getName());
            return true;
        }
        System.out.println("Incorrect PIN, please try again.");
        return false;
    }

    public void deposit(double amount)  { currentAccount.deposit(amount); }
    public void withdrawal(double amount) { currentAccount.withdraw(amount); }
    public void checkBalance() {
        System.out.println("Balance: $" + currentAccount.getBalance());
    }
}