package AtmBanking;

public interface Transactable {
    boolean withdraw(double amount) throws BankException;
    void deposit(double amount) throws BankException;
    boolean transfer(Account recipient, double amount) throws BankException;
}
