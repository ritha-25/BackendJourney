package AtmBanking;

public interface Transactable {
    boolean withdraw(double amount) throws BankException;
    void deposit(double amount) throws BankException;
}
