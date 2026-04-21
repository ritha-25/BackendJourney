public class Atm {
    private Account currentAccount;

    public boolean login(Account account, String pin) {
        if (account.getOwner().checkPin(pin)) {
            this.currentAccount = account;
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

    public void checkBalance() {
        System.out.println("Current balance: $" + currentAccount.getBalance());
    }
}